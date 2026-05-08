param(
    [string]$TargetRoot = "C:\mycode\bobiDelight\示范文件\示范mod\农夫乐事附属参考",
    [string]$GameVersion = "1.20.1"
)

$ErrorActionPreference = "Stop"
$ProgressPreference = "SilentlyContinue"

Add-Type -AssemblyName System.IO.Compression.FileSystem

$loaderPreference = @("forge", "neoforge", "fabric", "quilt")

$addons = @(
    [ordered]@{ Slug = "ends-delight"; Order = 1; Focus = "末地主题料理、战利品与维度食材扩展"; Why = "高下载量，适合研究如何把维度主题食材接进 Farmer's Delight 菜谱链。" },
    [ordered]@{ Slug = "chefs-delight"; Order = 2; Focus = "村民职业、交易与工作站扩展"; Why = "适合参考附属模组如何从纯食物内容延伸到村民生态。" },
    [ordered]@{ Slug = "oceans-delight"; Order = 3; Focus = "海洋食材、鱼获处理与海鲜菜谱"; Why = "适合参考生物掉落到烹饪成品的一整套闭环。" },
    [ordered]@{ Slug = "more-delight"; Order = 4; Focus = "额外菜肴、刀具与食材体系"; Why = "结构较直白，适合快速理解经典附属模组的资源组织。" },
    [ordered]@{ Slug = "brewin-and-chewin"; Order = 5; Focus = "饮品、发酵与料理工作流程"; Why = "适合借鉴非主食方向的内容扩展和方块交互。" },
    [ordered]@{ Slug = "delightful"; Order = 6; Focus = "甜点、兼容向配方与额外食材"; Why = "知名度高，适合研究偏轻量的内容增补与兼容写法。" },
    [ordered]@{ Slug = "crabbers-delight"; Order = 7; Focus = "海洋生物主题食材与料理"; Why = "适合对比 Ocean's Delight 的海鲜内容组织方式。" },
    [ordered]@{ Slug = "nethers-delight"; Order = 8; Focus = "下界掉落、危险环境食材与料理"; Why = "常见参考对象，适合看跨维度主题附属的依赖与数据布局。" },
    [ordered]@{ Slug = "cultural-delights"; Order = 9; Focus = "地域文化菜系、作物与成品链路"; Why = "适合借鉴地域风味模组如何组织命名、素材和食谱。" },
    [ordered]@{ Slug = "corn-delight"; Order = 10; Focus = "单核心作物驱动的扩展路线"; Why = "适合研究从一个作物出发扩成完整食品支线。" },
    [ordered]@{ Slug = "veggies-delight"; Order = 11; Focus = "蔬菜、结构生成与世界内容补充"; Why = "适合参考世界生成与食物内容共存时的资源层次。" },
    [ordered]@{ Slug = "packed-up"; Order = 12; Focus = "食物打包、存储与物品形态变化"; Why = "适合研究附属模组不做新作物时如何围绕 FD 做功能侧扩展。" }
)

function Invoke-ModrinthJson {
    param([string]$Uri)
    Invoke-RestMethod -Uri $Uri -Headers @{ "User-Agent" = "GitHubCopilot-bobiDelight-addon-survey/1.0" }
}

function Get-BestVersion {
    param(
        [object[]]$Versions,
        [string[]]$PreferredLoaders,
        [string]$RequiredGameVersion
    )

    $matching = @($Versions | Where-Object { $_.game_versions -contains $RequiredGameVersion })
    if (-not $matching.Count) {
        return $null
    }

    foreach ($loader in $PreferredLoaders) {
        $candidate = $matching | Where-Object { $_.loaders -contains $loader } | Sort-Object date_published -Descending | Select-Object -First 1
        if ($candidate) {
            return $candidate
        }
    }

    return ($matching | Sort-Object date_published -Descending | Select-Object -First 1)
}

function Remove-DirectoryIfExists {
    param([string]$Path)
    if (Test-Path -LiteralPath $Path) {
        Remove-Item -LiteralPath $Path -Recurse -Force
    }
}

function Expand-JarToDirectory {
    param(
        [string]$JarPath,
        [string]$Destination
    )

    Remove-DirectoryIfExists -Path $Destination
    New-Item -ItemType Directory -Path $Destination -Force | Out-Null
    [System.IO.Compression.ZipFile]::ExtractToDirectory($JarPath, $Destination)
}

function Get-ForgeMetadata {
    param([string]$TomlPath)

    $text = Get-Content -LiteralPath $TomlPath -Raw -Encoding UTF8
    $modId = [regex]::Match($text, '(?m)^\s*modId\s*=\s*"([^"]+)"').Groups[1].Value
    $displayName = [regex]::Match($text, '(?m)^\s*displayName\s*=\s*"([^"]+)"').Groups[1].Value
    $version = [regex]::Match($text, '(?m)^\s*version\s*=\s*"([^"]+)"').Groups[1].Value
    $license = [regex]::Match($text, '(?m)^\s*license\s*=\s*"([^"]+)"').Groups[1].Value

    $dependencyMatches = [regex]::Matches($text, '(?ms)\[\[dependencies\.[^\]]+\]\](.*?)((?=\n\[\[)|\z)')
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
        ModId = $modId
        Name = $displayName
        Version = $version
        License = $license
        Dependencies = @($dependencies | Sort-Object -Unique)
    }
}

function Get-FabricMetadata {
    param([string]$JsonPath)

    $json = Get-Content -LiteralPath $JsonPath -Raw -Encoding UTF8 | ConvertFrom-Json
    $dependencies = @()
    if ($json.depends) {
        $dependencies += @($json.depends.PSObject.Properties.Name)
    }
    if ($json.recommends) {
        $dependencies += @($json.recommends.PSObject.Properties.Name)
    }

    [PSCustomObject]@{
        Loader = "fabric"
        ModId = $json.id
        Name = $json.name
        Version = $json.version
        License = if ($json.license -is [System.Array]) { $json.license -join ', ' } else { [string]$json.license }
        Dependencies = @($dependencies | Sort-Object -Unique)
    }
}

function Get-AddonMetadata {
    param([string]$ExtractedRoot)

    $fabricJson = Join-Path $ExtractedRoot "fabric.mod.json"
    $quiltJson = Join-Path $ExtractedRoot "quilt.mod.json"
    $modsToml = Join-Path $ExtractedRoot "META-INF\mods.toml"

    if (Test-Path -LiteralPath $fabricJson) {
        return Get-FabricMetadata -JsonPath $fabricJson
    }

    if (Test-Path -LiteralPath $quiltJson) {
        return Get-FabricMetadata -JsonPath $quiltJson
    }

    if (Test-Path -LiteralPath $modsToml) {
        return Get-ForgeMetadata -TomlPath $modsToml
    }

    [PSCustomObject]@{
        Loader = "unknown"
        ModId = ""
        Name = ""
        Version = ""
        License = ""
        Dependencies = @()
    }
}

function Get-TopLevelClassPackages {
    param([string]$ExtractedRoot)

    $directories = Get-ChildItem -LiteralPath $ExtractedRoot -Directory -ErrorAction SilentlyContinue |
        Where-Object { (Get-ChildItem -LiteralPath $_.FullName -Recurse -Filter *.class -ErrorAction SilentlyContinue | Select-Object -First 1) }

    @($directories.Name | Sort-Object)
}

function Get-Counts {
    param([string]$ExtractedRoot)

    $recipes = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]recipes[\\/]' })
    $lootTables = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]loot_tables[\\/]' })
    $tags = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]tags[\\/]' })
    $lang = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]lang[\\/]' })
    $models = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]models[\\/]' })
    $blockstates = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]blockstates[\\/]' })
    $worldgen = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/](worldgen|configured_feature|placed_feature|biome_modifier)[\\/]' })
    $textures = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Include *.png,*.mcmeta -ErrorAction SilentlyContinue | Where-Object { $_.FullName -match '[\\/]textures[\\/]' })
    $classFiles = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.class -ErrorAction SilentlyContinue)
    $mixins = @(Get-ChildItem -LiteralPath $ExtractedRoot -Recurse -File -Filter *.json -ErrorAction SilentlyContinue | Where-Object { $_.Name -match 'mixin' })

    [PSCustomObject]@{
        ClassFiles = $classFiles.Count
        Recipes = $recipes.Count
        LootTables = $lootTables.Count
        Tags = $tags.Count
        LangFiles = $lang.Count
        Models = $models.Count
        Blockstates = $blockstates.Count
        Worldgen = $worldgen.Count
        Textures = $textures.Count
        Mixins = $mixins.Count
    }
}

function Get-DirectoryPresence {
    param([string]$ExtractedRoot)

    $checks = [ordered]@{
        "assets" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "assets")
        "data" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "data")
        "META-INF" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "META-INF")
        "fabric.mod.json" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "fabric.mod.json")
        "mods.toml" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "META-INF\mods.toml")
        "pack.mcmeta" = Test-Path -LiteralPath (Join-Path $ExtractedRoot "pack.mcmeta")
    }

    return $checks
}

function Format-Bool {
    param([bool]$Value)
    if ($Value) { return "是" }
    return "否"
}

function New-AddonReadme {
    param(
        [hashtable]$Addon,
        [object]$Project,
        [object]$Version,
        [object]$Metadata,
        [object]$Counts,
        [string[]]$TopPackages,
        [hashtable]$Presence,
        [string]$ReadmePath,
        [string]$JarFileName,
        [string]$SelectedLoader
    )

    $fdDepends = $Metadata.Dependencies -contains "farmersdelight"
    $depText = if ($Metadata.Dependencies.Count) { $Metadata.Dependencies -join ", " } else { "未在元数据中解析到" }
    $topPackageText = if ($TopPackages.Count) { $TopPackages -join ", " } else { "未发现 class 包根（可能为纯数据包或结构较特殊）" }
    $categories = if ($Project.categories) { $Project.categories -join ", " } else { "无" }
    $presenceLines = $Presence.GetEnumerator() | ForEach-Object { "- {0}: {1}" -f $_.Key, (Format-Bool -Value ([bool]$_.Value)) }

    $content = @"
# $($Project.title)

## 1. 基本信息

| 项目 | 内容 |
|------|------|
| Modrinth slug | $($Addon.Slug) |
| 下载量 | $($Project.downloads) |
| 选择版本 | $($Version.version_number) |
| 选择加载器 | $SelectedLoader |
| 适配 MC | $($Version.game_versions -join ', ') |
| 许可证 | $($Project.license.id) |
| 分类 | $categories |
| 原始文件 | $JarFileName |

## 2. 模组定位

- 研究焦点: $($Addon.Focus)
- 选入原因: $($Addon.Why)
- 项目简介: $($Project.description)

## 3. Check 结果

- 已成功下载并解压: 是
- 显式声明依赖 Farmer's Delight: $(Format-Bool -Value $fdDepends)
- 解析到的依赖项: $depText
- 元数据中的模组 ID: $(if ($Metadata.ModId) { $Metadata.ModId } else { '未解析到' })
- 元数据中的显示名: $(if ($Metadata.Name) { $Metadata.Name } else { '未解析到' })
- 元数据中的版本字段: $(if ($Metadata.Version) { $Metadata.Version } else { '未解析到' })
- 顶层 class 包根: $topPackageText

### 结构检查

$($presenceLines -join "`r`n")

### 内容规模统计

| 类型 | 数量 |
|------|------|
| class 文件 | $($Counts.ClassFiles) |
| recipes JSON | $($Counts.Recipes) |
| loot tables | $($Counts.LootTables) |
| tags JSON | $($Counts.Tags) |
| lang 文件 | $($Counts.LangFiles) |
| models JSON | $($Counts.Models) |
| blockstates JSON | $($Counts.Blockstates) |
| worldgen 相关 JSON | $($Counts.Worldgen) |
| 纹理文件 | $($Counts.Textures) |
| mixin 配置 | $($Counts.Mixins) |

## 4. 对 bobiDelight 的参考价值

- 如果你要补一整条内容链，这个模组最值得看的切入点是: $($Addon.Focus)
- 如果你要做玩法外延而不只加菜谱，这个模组的实现方向是: $($Addon.Why)
- 建议优先翻看 extracted 目录下的 metadata、assets、data 三层，再决定是否继续看 class 包。

## 5. 本地路径

- 解压目录: extracted
- 原始 jar: $JarFileName
"@

    Set-Content -LiteralPath $ReadmePath -Value $content -Encoding UTF8
}

function New-Guide {
    param(
        [object[]]$Manifest,
        [string]$GuidePath,
        [string]$RootPath,
        [string]$RequiredGameVersion
    )

    $rows = foreach ($item in $Manifest) {
        "| {0} | {1} | {2} | {3} | {4} | {5} |" -f $item.Order, $item.Title, $item.Loader, $item.Downloads, $item.MetadataModId, $item.Focus
    }

    $content = @"
# Farmer's Delight 附属模组参考指南

## 1. 这次怎么筛选

- 平台来源统一用 Modrinth，避免下载源不稳定。
- 版本统一锁定为 Minecraft $RequiredGameVersion。
- 优先选择下载量高、社区更常提到的 Farmer's Delight 附属模组。
- 加载器优先级固定为 forge > neoforge > fabric > quilt，便于和当前项目双端实现做对照。
- 每个模组都保留原始 jar、解压结果和单独说明，后续可以重复复查。

## 2. 已收集模组总表

| 序号 | 模组 | 加载器 | 下载量 | Mod ID | 研究焦点 |
|------|------|--------|--------|--------|----------|
$($rows -join "`r`n")

## 3. 建议阅读顺序

1. 先看 End's Delight、Nether's Delight、Ocean's Delight，理解“主题维度/生态食材”怎么接进 FD 内容链。
2. 再看 More Delight、Delightful、Corn Delight，理解“标准附属模组”的资源布局和 recipe/tag 组织。
3. 然后看 Chef's Delight、Packed Up、Brewin' And Chewin'，理解如何把 FD 扩到村民、存储、酿造等非纯食物方向。
4. 最后看 Cultural Delights、Veggies Delight、Crabber's Delight，对比地域风味和世界内容扩展的写法。

## 4. 对 bobiDelight 最有价值的观察点

- 作物/食材型附属通常都把重点放在 data 目录，尤其是 recipes、tags、loot_tables、worldgen。
- 功能型附属更容易在 class 层做事件、菜单、方块实体或村民逻辑，适合对照你当前代码结构找扩展位。
- 真正值得借鉴的不是“菜名”，而是命名规则、资源分层、依赖声明和内容闭环组织方式。
- 如果一个模组 worldgen 文件很多，优先检查其世界生成和掉落链路是否解耦，这对你继续扩作物最有参考价值。

## 5. 本地目录约定

- 根目录: $RootPath
- 每个模组一个独立子目录，内部包含原始 jar、extracted 目录和 README.md。
- 可先从单模组 README 看 check 结果，再决定是否深入阅读 extracted 资源。
"@

    Set-Content -LiteralPath $GuidePath -Value $content -Encoding UTF8
}

New-Item -ItemType Directory -Path $TargetRoot -Force | Out-Null

$manifest = @()

foreach ($addon in $addons) {
    $project = Invoke-ModrinthJson -Uri ("https://api.modrinth.com/v2/project/{0}" -f $addon.Slug)
    $versions = Invoke-ModrinthJson -Uri ("https://api.modrinth.com/v2/project/{0}/version" -f $addon.Slug)
    $selectedVersion = Get-BestVersion -Versions $versions -PreferredLoaders $loaderPreference -RequiredGameVersion $GameVersion

    if (-not $selectedVersion) {
        throw "未找到 $($addon.Slug) 的 $GameVersion 可用版本。"
    }

    $selectedLoader = ($loaderPreference | Where-Object { $selectedVersion.loaders -contains $_ } | Select-Object -First 1)
    if (-not $selectedLoader) {
        $selectedLoader = $selectedVersion.loaders[0]
    }

    $file = $selectedVersion.files | Where-Object { $_.primary } | Select-Object -First 1
    if (-not $file) {
        $file = $selectedVersion.files | Select-Object -First 1
    }

    $folderName = "{0:D2}_{1}" -f $addon.Order, $addon.Slug
    $addonRoot = Join-Path $TargetRoot $folderName
    $extractRoot = Join-Path $addonRoot "extracted"
    $jarPath = Join-Path $addonRoot $file.filename
    $readmePath = Join-Path $addonRoot "README.md"

    New-Item -ItemType Directory -Path $addonRoot -Force | Out-Null
    Invoke-WebRequest -Uri $file.url -OutFile $jarPath
    Expand-JarToDirectory -JarPath $jarPath -Destination $extractRoot

    $metadata = Get-AddonMetadata -ExtractedRoot $extractRoot
    $counts = Get-Counts -ExtractedRoot $extractRoot
    $topPackages = Get-TopLevelClassPackages -ExtractedRoot $extractRoot
    $presence = Get-DirectoryPresence -ExtractedRoot $extractRoot

    New-AddonReadme -Addon $addon -Project $project -Version $selectedVersion -Metadata $metadata -Counts $counts -TopPackages $topPackages -Presence $presence -ReadmePath $readmePath -JarFileName $file.filename -SelectedLoader $selectedLoader

    $manifest += [PSCustomObject]@{
        Order = $addon.Order
        Slug = $addon.Slug
        Title = $project.title
        Downloads = $project.downloads
        Loader = $selectedLoader
        Focus = $addon.Focus
        Why = $addon.Why
        Folder = $folderName
        Version = $selectedVersion.version_number
        MetadataModId = $metadata.ModId
        Dependencies = @($metadata.Dependencies)
        Counts = $counts
    }
}

$manifestPath = Join-Path $TargetRoot "addons-manifest.json"
$guidePath = Join-Path $TargetRoot "总览指南.md"

$manifest | ConvertTo-Json -Depth 6 | Set-Content -LiteralPath $manifestPath -Encoding UTF8
New-Guide -Manifest $manifest -GuidePath $guidePath -RootPath $TargetRoot -RequiredGameVersion $GameVersion

Write-Host ("已完成 {0} 个 Farmer's Delight 附属模组的下载、解压与文档生成。输出目录: {1}" -f $manifest.Count, $TargetRoot)