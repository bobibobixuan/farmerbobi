# Export bobiDelight texture assets with Chinese names and categorized folders
param(
    [string]$SourceDir = "fabric\src\main\resources\assets\bobidelight",
    [string]$OutputDir = "导出素材"
)

# Chinese name mapping based on zh_cn.json
$nameMap = @{
    # Crops
    "corn"            = "玉米"
    "soybean"         = "大豆"
    "chili_pepper"    = "辣椒"
    "garlic"          = "大蒜"
    "ginger"          = "姜"
    "cucumber"        = "黄瓜"
    "eggplant"        = "茄子"
    "radish"          = "白萝卜"
    "green_onion"     = "小葱"
    "cilantro"        = "香菜"
    "lettuce"         = "生菜"
    "celery"          = "芹菜"
    "bell_pepper"     = "青椒"
    "peanut"          = "花生"
    "sesame"          = "芝麻"
    "mung_bean"       = "绿豆"
    "strawberry"      = "草莓"
    "blueberry"       = "蓝莓"
    "pineapple"       = "菠萝"
    "grape"           = "葡萄"
    "lemon"           = "柠檬"
    "sweet_potato"    = "红薯"
    "taro"            = "芋头"
    "bamboo_shoot"    = "竹笋"
    "mint"            = "薄荷"
    "rosemary"        = "迷迭香"
    "oyster_mushroom" = "平菇"
    "spinach"         = "菠菜"
    "mustard_green"   = "芥菜"
    "seaweed"         = "紫菜"
    "bok_choy"        = "青江菜"
    "shiitake"        = "香菇"
    "chinese_yam"     = "山药"
    # Fast food
    "chicken_wings"   = "鸡翅"
    "hamburger"       = "汉堡"
    "fries"           = "薯条"
    "pizza"           = "披萨"
    "ice_cream"       = "冰淇淋"
    # Feast
    "hot_pot"         = "火锅"
}

$stageMap = @{
    "stage0" = "种子阶段"
    "stage4" = "生长阶段1"
    "stage5" = "生长阶段2"
    "stage6" = "生长阶段3"
    "stage7" = "成熟阶段"
}

# Remove output dir if exists and recreate
if (Test-Path $OutputDir) {
    Remove-Item -Recurse -Force $OutputDir
}
New-Item -ItemType Directory -Force -Path "$OutputDir\01_生长阶段" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\02_成品作物" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\03_种子" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\04_野生植物" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\05_容器箱子" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\06_容器袋子" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\07_快餐" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\08_盛宴" | Out-Null
New-Item -ItemType Directory -Force -Path "$OutputDir\09_图标" | Out-Null

# Process icon
$iconSrc = Join-Path $SourceDir "icon.png"
if (Test-Path $iconSrc) {
    Copy-Item $iconSrc "$OutputDir\09_图标\波比乐事图标.png"
    Write-Host "  图标: 波比乐事图标.png"
}

# Process texture files
$textureDir = Join-Path $SourceDir "textures"

# Block textures
$blockDir = Join-Path $textureDir "block"
if (Test-Path $blockDir) {
    $blockFiles = Get-ChildItem $blockDir -Filter "*.png"
    foreach ($file in $blockFiles) {
        $name = $file.BaseName -replace "^bobixuan_", ""
        
        # Determine category and generate Chinese filename
        $chineseName = $null
        $category = $null
        
        # Wild plants
        if ($name -match "^wild_(.+)$") {
            $cropId = $Matches[1]
            if ($nameMap.ContainsKey($cropId)) {
                $chineseName = "野生$($nameMap[$cropId])"
                $category = "04_野生植物"
            }
        }
        # Crop growth stages
        elseif ($name -match "^(.+)_crop_stage(\d+)$") {
            $cropId = $Matches[1]
            $stage = $Matches[2]
            if ($nameMap.ContainsKey($cropId)) {
                $stageLabel = if ($stageMap.ContainsKey("stage$stage")) { $stageMap["stage$stage"] } else { "阶段$stage" }
                $chineseName = "$($nameMap[$cropId])_${stageLabel}"
                $category = "01_生长阶段"
            }
        }
        # Feast blocks (hot pot stages)
        elseif ($name -match "^(.+)_stage(\d+)$") {
            $feastId = $Matches[1]
            $stage = $Matches[2]
            if ($nameMap.ContainsKey($feastId)) {
                $stageLabel = if ($stageMap.ContainsKey("stage$stage")) { $stageMap["stage$stage"] } else { "阶段$stage" }
                $chineseName = "$($nameMap[$feastId])_${stageLabel}"
                $category = "08_盛宴"
            }
        }
        # Feast block side/bottom
        elseif ($name -match "^(.+)_(bottom|side)$") {
            $feastId = $Matches[1]
            $partMap = @{ "side" = "侧面"; "bottom" = "底面" }
            $part = $Matches[2]
            if ($nameMap.ContainsKey($feastId)) {
                $chineseName = "$($nameMap[$feastId])_$($partMap[$part])"
                $category = "08_盛宴"
            }
        }
        # Crate textures (side/top)
        elseif ($name -match "^(.+)_crate_(side|top)$") {
            $cropId = $Matches[1]
            $partMap = @{ "side" = "侧面"; "top" = "顶面" }
            $part = $Matches[2]
            if ($nameMap.ContainsKey($cropId)) {
                $chineseName = "箱装$($nameMap[$cropId])_$($partMap[$part])"
                $category = "05_容器箱子"
            }
        }
        # Bag textures (side/top/bottom)
        elseif ($name -match "^(.+)_bag_(side|top|bottom)$") {
            $cropId = $Matches[1]
            $partMap = @{ "side" = "侧面"; "top" = "顶面"; "bottom" = "底面" }
            $part = $Matches[2]
            if ($nameMap.ContainsKey($cropId)) {
                $chineseName = "袋装$($nameMap[$cropId])_$($partMap[$part])"
                $category = "06_容器袋子"
            }
        }
        
        if ($chineseName) {
            $destPath = Join-Path $OutputDir "$category\$chineseName.png"
            Copy-Item $file.FullName $destPath
            Write-Host "  [$category] $($file.Name) -> $chineseName.png"
        } else {
            Write-Host "  [未映射] $($file.Name)" -ForegroundColor Yellow
        }
    }
}

# Item textures
$itemDir = Join-Path $textureDir "item"
if (Test-Path $itemDir) {
    $itemFiles = Get-ChildItem $itemDir -Filter "*.png"
    foreach ($file in $itemFiles) {
        $name = $file.BaseName -replace "^bobixuan_", ""
        $chineseName = $null
        $category = $null
        
        # Seeds
        if ($name -match "^(.+)_seeds$") {
            $cropId = $Matches[1]
            if ($nameMap.ContainsKey($cropId)) {
                $chineseName = "$($nameMap[$cropId])种子"
                $category = "03_种子"
            }
        }
        # Crop items (raw produce)
        elseif ($nameMap.ContainsKey($name)) {
            $chineseName = $nameMap[$name]
            # Determine if it's a crop or fast food
            $fastFood = @("chicken_wings", "hamburger", "fries", "pizza", "ice_cream")
            $feast = @("hot_pot")
            if ($feast -contains $name) {
                $category = "08_盛宴"
            } elseif ($fastFood -contains $name) {
                $category = "07_快餐"
            } else {
                $category = "02_成品作物"
            }
        }
        elseif ($name -eq "cilantro") {
            $chineseName = "香菜"
            $category = "02_成品作物"
        }
        
        if ($chineseName) {
            $destPath = Join-Path $OutputDir "$category\$chineseName.png"
            Copy-Item $file.FullName $destPath
            Write-Host "  [$category] $($file.Name) -> $chineseName.png"
        } else {
            Write-Host "  [未映射] $($file.Name)" -ForegroundColor Yellow
        }
    }
}

Write-Host ""
Write-Host "============================================" -ForegroundColor Green
Write-Host "  导出完成! 输出目录: $OutputDir" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Green

# Print summary
Get-ChildItem $OutputDir -Directory | ForEach-Object {
    $count = (Get-ChildItem $_.FullName -Filter "*.png").Count
    Write-Host "  $($_.Name): $count 个文件"
}
$total = (Get-ChildItem $OutputDir -Recurse -Filter "*.png").Count
Write-Host "  总计: $total 个PNG文件"
