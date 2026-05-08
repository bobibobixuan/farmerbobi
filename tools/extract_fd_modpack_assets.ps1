param(
    [string]$SourceRoot = "C:\mycode\bobiDelight\设计文档\示范文件\示范mod\整合包模组",
    [string]$TargetRoot = "C:\mycode\bobiDelight\设计文档\示范文件\示范mod\整合包农夫乐事素材参考"
)

$ErrorActionPreference = "Stop"
$ProgressPreference = "SilentlyContinue"

Add-Type -AssemblyName System.IO.Compression.FileSystem

$Utf8NoBom = New-Object System.Text.UTF8Encoding($false)

function Write-Utf8File {
    param(
        [string]$Path,
        [string]$Content
    )

    $directory = Split-Path -Parent $Path
    if ($directory -and -not (Test-Path -LiteralPath $directory)) {
        New-Item -ItemType Directory -Path $directory -Force | Out-Null
    }

    [System.IO.File]::WriteAllText($Path, $Content, $Utf8NoBom)
}

function Remove-DirectoryIfExists {
    param([string]$Path)

    if (Test-Path -LiteralPath $Path) {
        Remove-Item -LiteralPath $Path -Recurse -Force
    }
}

function ConvertTo-Slug {
    param([string]$Text)

    if (-not $Text) {
        return "mod"
    }

    $slug = $Text.ToLowerInvariant()
    $slug = [regex]::Replace($slug, '[^a-z0-9]+', '-')
    $slug = $slug.Trim('-')

    if (-not $slug) {
        return "mod"
    }

    return $slug
}

function Get-ZipEntryText {
    param(
        [System.IO.Compression.ZipArchive]$Zip,
        [string]$EntryName
    )

    $entry = $Zip.Entries | Where-Object { $_.FullName -ieq $EntryName } | Select-Object -First 1
    if (-not $entry) {
        return $null
    }

    $reader = New-Object System.IO.StreamReader($entry.Open())
    try {
        return $reader.ReadToEnd()
    }
    finally {
        $reader.Dispose()
    }
}

function Get-ForgeMetadataFromText {
    param([string]$Text)

    $dependencyMatches = [regex]::Matches($Text, '(?ms)\[\[dependencies\.[^\]]+\]\](.*?)(?=\n\[\[|\z)')
    $dependencies = @()
    foreach ($match in $dependencyMatches) {
        $block = $match.Groups[1].Value
        $depId = [regex]::Match($block, '(?m)^\s*modId\s*=\s*"([^"]+)"').Groups[1].Value
        if ($depId) {
            $dependencies += $depId
        }
    }

    [PSCustomObject]@{
        Loader = "forge"
        ModId = [regex]::Match($Text, '(?m)^\s*modId\s*=\s*"([^"]+)"').Groups[1].Value
        Name = [regex]::Match($Text, '(?m)^\s*displayName\s*=\s*"([^"]+)"').Groups[1].Value
        Version = [regex]::Match($Text, '(?m)^\s*version\s*=\s*"([^"]+)"').Groups[1].Value
        Dependencies = @($dependencies | Sort-Object -Unique)
    }
}

function Get-FabricMetadataFromText {
    param([string]$Text)

    $json = $Text | ConvertFrom-Json
    $dependencies = @()
    if ($json.depends) {
        $dependencies += @($json.depends.PSObject.Properties.Name)
    }
    if ($json.recommends) {
        $dependencies += @($json.recommends.PSObject.Properties.Name)
    }

    [PSCustomObject]@{
        Loader = "fabric"
        ModId = [string]$json.id
        Name = [string]$json.name
        Version = [string]$json.version
        Dependencies = @($dependencies | Sort-Object -Unique)
    }
}

function Get-UnknownMetadata {
    [PSCustomObject]@{
        Loader = "unknown"
        ModId = ""
        Name = ""
        Version = ""
        Dependencies = @()
    }
}

function Copy-ZipEntry {
    param(
        [System.IO.Compression.ZipArchiveEntry]$Entry,
        [string]$DestinationPath
    )

    $parent = Split-Path -Parent $DestinationPath
    if ($parent -and -not (Test-Path -LiteralPath $parent)) {
        New-Item -ItemType Directory -Path $parent -Force | Out-Null
    }

    $sourceStream = $Entry.Open()
    $targetStream = [System.IO.File]::Create($DestinationPath)
    try {
        $sourceStream.CopyTo($targetStream)
    }
    finally {
        $targetStream.Dispose()
        $sourceStream.Dispose()
    }
}

function Get-JarInfo {
    param([System.IO.FileInfo]$JarFile)

    $zip = [System.IO.Compression.ZipFile]::OpenRead($JarFile.FullName)
    try {
        $entries = @($zip.Entries | Where-Object { -not [string]::IsNullOrWhiteSpace($_.Name) })
        $modsTomlText = Get-ZipEntryText -Zip $zip -EntryName 'META-INF/mods.toml'
        $fabricText = Get-ZipEntryText -Zip $zip -EntryName 'fabric.mod.json'
        $quiltText = Get-ZipEntryText -Zip $zip -EntryName 'quilt.mod.json'

        if ($modsTomlText) {
            $metadata = Get-ForgeMetadataFromText -Text $modsTomlText
            $rawText = $modsTomlText
        }
        elseif ($fabricText) {
            $metadata = Get-FabricMetadataFromText -Text $fabricText
            $rawText = $fabricText
        }
        elseif ($quiltText) {
            $metadata = Get-FabricMetadataFromText -Text $quiltText
            $rawText = $quiltText
        }
        else {
            $metadata = Get-UnknownMetadata
            $rawText = ""
        }

        $assetPaths = @($entries | Where-Object { $_.FullName -match '^assets/[^/]+/.+' } | ForEach-Object { $_.FullName })
        $dataPaths = @($entries | Where-Object { $_.FullName -match '^data/[^/]+/.+' } | ForEach-Object { $_.FullName })

        $hasFarmersDelightDependency = $false
        if ($metadata.Dependencies -contains 'farmersdelight') {
            $hasFarmersDelightDependency = $true
        }
        elseif ($rawText -match 'farmersdelight|farmers_delight|farmer.?s delight') {
            $hasFarmersDelightDependency = $true
        }

        $isDelightFamily = $JarFile.Name -match 'delight|Delight|farmers|Farmers'
        if (-not ($hasFarmersDelightDependency -or $isDelightFamily)) {
            return $null
        }

        $texturePaths = @($assetPaths | Where-Object { $_ -match '^assets/[^/]+/textures/.+\.(png|mcmeta)$' } | Sort-Object)
        $modelPaths = @($assetPaths | Where-Object { $_ -match '^assets/[^/]+/models/.+\.json$' } | Sort-Object)
        $blockstatePaths = @($assetPaths | Where-Object { $_ -match '^assets/[^/]+/blockstates/.+\.json$' } | Sort-Object)
        $langPaths = @($assetPaths | Where-Object { $_ -match '^assets/[^/]+/lang/.+\.json$' } | Sort-Object)
        $recipePaths = @($dataPaths | Where-Object { $_ -match '^data/[^/]+/recipes/.+\.json$' } | Sort-Object)
        $lootTablePaths = @($dataPaths | Where-Object { $_ -match '^data/[^/]+/loot_tables/.+\.json$' } | Sort-Object)
        $tagPaths = @($dataPaths | Where-Object { $_ -match '^data/[^/]+/tags/.+\.json$' } | Sort-Object)
        $worldgenPaths = @($dataPaths | Where-Object { $_ -match '^data/[^/]+/(worldgen|configured_feature|placed_feature|biome_modifier)/.+' } | Sort-Object)

        $assetNamespaces = @($assetPaths | ForEach-Object { ($_ -split '/')[1] } | Sort-Object -Unique)
        $dataNamespaces = @($dataPaths | ForEach-Object { ($_ -split '/')[1] } | Sort-Object -Unique)

        [PSCustomObject]@{
            FileName = $JarFile.Name
            FullName = $JarFile.FullName
            Loader = $metadata.Loader
            ModId = $metadata.ModId
            DisplayName = if ($metadata.Name) { $metadata.Name } else { [System.IO.Path]::GetFileNameWithoutExtension($JarFile.Name) }
            Version = $metadata.Version
            Dependencies = @($metadata.Dependencies)
            HasFarmersDelightDependency = $hasFarmersDelightDependency
            Relation = if ($hasFarmersDelightDependency) { 'direct' } else { 'delight-family' }
            AssetNamespaces = @($assetNamespaces)
            DataNamespaces = @($dataNamespaces)
            TexturePaths = $texturePaths
            ModelPaths = $modelPaths
            BlockstatePaths = $blockstatePaths
            LangPaths = $langPaths
            RecipePaths = $recipePaths
            LootTablePaths = $lootTablePaths
            TagPaths = $tagPaths
            WorldgenPaths = $worldgenPaths
            Counts = [ordered]@{
                Textures = $texturePaths.Count
                Models = $modelPaths.Count
                Blockstates = $blockstatePaths.Count
                LangFiles = $langPaths.Count
                Recipes = $recipePaths.Count
                LootTables = $lootTablePaths.Count
                Tags = $tagPaths.Count
                Worldgen = $worldgenPaths.Count
            }
        }
    }
    finally {
        $zip.Dispose()
    }
}

function Expand-RelevantEntries {
    param(
        [string]$JarPath,
        [string]$DestinationRoot
    )

    $zip = [System.IO.Compression.ZipFile]::OpenRead($JarPath)
    try {
        foreach ($entry in $zip.Entries) {
            if ([string]::IsNullOrWhiteSpace($entry.Name)) {
                continue
            }

            $fullName = $entry.FullName -replace '\\', '/'
            if (
                $fullName -match '^(assets|data)/.+' -or
                $fullName -ieq 'META-INF/mods.toml' -or
                $fullName -ieq 'fabric.mod.json' -or
                $fullName -ieq 'quilt.mod.json' -or
                $fullName -ieq 'pack.mcmeta'
            ) {
                $targetPath = Join-Path $DestinationRoot ($fullName -replace '/', '\\')
                Copy-ZipEntry -Entry $entry -DestinationPath $targetPath
            }
        }
    }
    finally {
        $zip.Dispose()
    }
}

function New-ModReadme {
    param(
        [object]$Info,
        [string]$FolderPath,
        [string]$SourceRoot
    )

    $dependencyText = if ($Info.Dependencies.Count) { $Info.Dependencies -join ', ' } else { '未解析到依赖项' }
    $assetNamespaceText = if ($Info.AssetNamespaces.Count) { $Info.AssetNamespaces -join ', ' } else { '无 assets 命名空间' }
    $dataNamespaceText = if ($Info.DataNamespaces.Count) { $Info.DataNamespaces -join ', ' } else { '无 data 命名空间' }
    $sourceRelative = $Info.FullName.Replace($SourceRoot.TrimEnd('\\'), '').TrimStart('\\')

    $content = @"
# $($Info.DisplayName)

## 基本信息

| 项目 | 内容 |
|------|------|
| 原始文件 | $($Info.FileName) |
| 相对来源 | $sourceRelative |
| 加载器 | $($Info.Loader) |
| Mod ID | $(if ($Info.ModId) { $Info.ModId } else { '未解析到' }) |
| 版本 | $(if ($Info.Version) { $Info.Version } else { '未解析到' }) |
| 与 Farmer's Delight 关系 | $(if ($Info.Relation -eq 'direct') { '直接依赖/显式关联' } else { 'Delight 系扩展，元数据未显式声明 FD 依赖' }) |
| Farmer's Delight 依赖检测 | $(if ($Info.HasFarmersDelightDependency) { '是' } else { '否' }) |

## 素材范围

- assets 命名空间: $assetNamespaceText
- data 命名空间: $dataNamespaceText
- 依赖项: $dependencyText

## 统计

| 类型 | 数量 |
|------|------|
| 贴图 | $($Info.Counts.Textures) |
| 模型 | $($Info.Counts.Models) |
| Blockstate | $($Info.Counts.Blockstates) |
| 语言文件 | $($Info.Counts.LangFiles) |
| 配方 | $($Info.Counts.Recipes) |
| Loot Table | $($Info.Counts.LootTables) |
| Tag | $($Info.Counts.Tags) |
| Worldgen | $($Info.Counts.Worldgen) |

## 目录约定

- extracted/assets: 原始资源贴图、模型、语言等素材
- extracted/data: 配方、标签、世界生成等数据资源
- asset-index.json: 当前模组的可检索素材索引
"@

    Write-Utf8File -Path (Join-Path $FolderPath 'README.md') -Content ($content.Trim() + "`r`n")
}

function New-OverviewMarkdown {
    param(
        [object[]]$Manifest,
        [string]$OutputPath
    )

    $direct = @($Manifest | Where-Object { $_.Relation -eq 'direct' })
    $family = @($Manifest | Where-Object { $_.Relation -eq 'delight-family' })
    $topTexture = @($Manifest | Sort-Object { $_.Counts.Textures } -Descending | Select-Object -First 10)

    $lines = @()
    $lines += '# 整合包农夫乐事关联素材总览'
    $lines += ''
    $lines += '- 来源目录: 设计文档/示范文件/示范mod/整合包模组'
    $lines += '- 输出目录: 设计文档/示范文件/示范mod/整合包农夫乐事素材参考'
    $lines += '- 整理目标: 先复用整合包里已经做得足够好的 FD / Delight 系素材，再决定哪些内容需要 bobiDelight 自制。'
    $lines += ''
    $lines += '## 分类结果'
    $lines += ''
    $lines += ("- 直接依赖或显式关联 Farmer's Delight 的模组: {0}" -f $direct.Count)
    $lines += ("- Delight 系但元数据未显式声明 FD 依赖的模组: {0}" -f $family.Count)
    $lines += ("- 总计整理模组数: {0}" -f $Manifest.Count)
    $lines += ''
    $lines += '## 模组清单'
    $lines += ''
    $lines += '| 序号 | 模组 | 关系 | 加载器 | Mod ID | 贴图 | 模型 | 配方 | 文件夹 |'
    $lines += '|------|------|------|--------|--------|------|------|------|--------|'
    foreach ($item in $Manifest) {
        $lines += ("| {0} | {1} | {2} | {3} | {4} | {5} | {6} | {7} | {8} |" -f $item.Order, $item.DisplayName.Replace('|','/'), $(if ($item.Relation -eq 'direct') { '直接关联' } else { 'Delight 系' }), $item.Loader, $(if ($item.ModId) { $item.ModId } else { '未解析到' }), $item.Counts.Textures, $item.Counts.Models, $item.Counts.Recipes, $item.Folder)
    }
    $lines += ''
    $lines += '## 优先复用素材建议'
    $lines += ''
    foreach ($item in $topTexture) {
        $namespaces = if ($item.AssetNamespaces.Count) { $item.AssetNamespaces -join ', ' } else { '无 assets 命名空间' }
        $lines += ("- {0}: 贴图 {1} 张，优先检查 extracted/assets 下的 {2} 命名空间。" -f $item.DisplayName, $item.Counts.Textures, $namespaces)
    }
    $lines += ''
    $lines += '## 使用建议'
    $lines += ''
    $lines += '- 先看每个模组目录下的 README.md 和 asset-index.json，再决定是否直接复用。'
    $lines += '- 直接复用时优先参考命名、分层和配色，不要无差别整包搬运。'
    $lines += '- 遇到运行问题，优先查用户指定日志目录: C:/Users/21996/Desktop/1.20.1/.minecraft/versions/波比打金服1.20.1-Forge_47.4.6/logs'

    Write-Utf8File -Path $OutputPath -Content (($lines -join "`r`n") + "`r`n")
}

if (-not (Test-Path -LiteralPath $SourceRoot)) {
    throw "SourceRoot 不存在: $SourceRoot"
}

$candidateFiles = @(Get-ChildItem -LiteralPath $SourceRoot -File -Filter *.jar | Where-Object { $_.Name -match 'delight|Delight|farmers|Farmers' } | Sort-Object Name)
if (-not $candidateFiles.Count) {
    throw "未在 $SourceRoot 找到 delight/farmers 相关 jar 文件。"
}

$selected = @()
foreach ($file in $candidateFiles) {
    $info = Get-JarInfo -JarFile $file
    if ($info) {
        $selected += $info
    }
}

if (-not $selected.Count) {
    throw "未解析到可整理的 Farmer's Delight 关联模组。"
}

$ordered = @($selected | Sort-Object @{ Expression = { if ($_.Relation -eq 'direct') { 0 } else { 1 } } }, @{ Expression = { $_.DisplayName } })

Remove-DirectoryIfExists -Path $TargetRoot
New-Item -ItemType Directory -Path $TargetRoot -Force | Out-Null

$manifest = @()
$usedFolderSlugs = @{}
$index = 1
foreach ($info in $ordered) {
    $slugSeed = if ($info.ModId) { $info.ModId } else { [System.IO.Path]::GetFileNameWithoutExtension($info.FileName) }
    $folderSlug = ConvertTo-Slug -Text $slugSeed
    if ($usedFolderSlugs.ContainsKey($folderSlug)) {
        $folderSlug = '{0}-{1}' -f $folderSlug, (ConvertTo-Slug -Text $info.Loader)
    }
    $suffixIndex = 2
    while ($usedFolderSlugs.ContainsKey($folderSlug)) {
        $folderSlug = '{0}-{1}-{2}' -f (ConvertTo-Slug -Text $slugSeed), (ConvertTo-Slug -Text $info.Loader), $suffixIndex
        $suffixIndex++
    }
    $usedFolderSlugs[$folderSlug] = $true

    $folderName = ('{0:D2}_{1}' -f $index, $folderSlug)
    $folderPath = Join-Path $TargetRoot $folderName
    $extractPath = Join-Path $folderPath 'extracted'

    New-Item -ItemType Directory -Path $folderPath -Force | Out-Null
    Expand-RelevantEntries -JarPath $info.FullName -DestinationRoot $extractPath

    $assetIndex = [ordered]@{
        Textures = $info.TexturePaths
        Models = $info.ModelPaths
        Blockstates = $info.BlockstatePaths
        LangFiles = $info.LangPaths
        Recipes = $info.RecipePaths
        LootTables = $info.LootTablePaths
        Tags = $info.TagPaths
        Worldgen = $info.WorldgenPaths
    }

    Write-Utf8File -Path (Join-Path $folderPath 'asset-index.json') -Content (($assetIndex | ConvertTo-Json -Depth 6) + "`r`n")
    New-ModReadme -Info $info -FolderPath $folderPath -SourceRoot $SourceRoot

    $manifest += [PSCustomObject]@{
        Order = $index
        Folder = $folderName
        FileName = $info.FileName
        DisplayName = $info.DisplayName
        Loader = $info.Loader
        ModId = $info.ModId
        Version = $info.Version
        Relation = $info.Relation
        HasFarmersDelightDependency = $info.HasFarmersDelightDependency
        Dependencies = $info.Dependencies
        AssetNamespaces = $info.AssetNamespaces
        DataNamespaces = $info.DataNamespaces
        Counts = $info.Counts
    }

    $index++
}

Write-Utf8File -Path (Join-Path $TargetRoot 'mods-manifest.json') -Content (($manifest | ConvertTo-Json -Depth 8) + "`r`n")
New-OverviewMarkdown -Manifest $manifest -OutputPath (Join-Path $TargetRoot '素材总览.md')

Write-Output ("整理完成: {0} 个模组 -> {1}" -f $manifest.Count, $TargetRoot)