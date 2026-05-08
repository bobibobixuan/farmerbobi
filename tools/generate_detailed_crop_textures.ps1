param(
    [string]$ProjectRoot = "C:\mycode\bobiDelight"
)

$ErrorActionPreference = "Stop"

Add-Type -AssemblyName System.Drawing

function New-Canvas {
    $bitmap = New-Object System.Drawing.Bitmap 16, 16
    $bitmap.MakeTransparent([System.Drawing.Color]::Transparent)
    return $bitmap
}

function New-Color([int]$r, [int]$g, [int]$b, [int]$a = 255) {
    return [System.Drawing.Color]::FromArgb($a, $r, $g, $b)
}

function Set-Pixel($bitmap, [int]$x, [int]$y, $color) {
    if ($x -lt 0 -or $x -ge 16 -or $y -lt 0 -or $y -ge 16) {
        return
    }

    $bitmap.SetPixel($x, $y, $color)
}

function Draw-Pixels($bitmap, $pixels, $color) {
    foreach ($pixel in $pixels) {
        Set-Pixel $bitmap $pixel[0] $pixel[1] $color
    }
}

function Pt([int]$x, [int]$y) {
    return ,@($x, $y)
}

function Draw-Column($bitmap, [int]$x, [int]$startY, [int]$endY, $dark, $mid, $light) {
    for ($y = $startY; $y -le $endY; $y++) {
        Set-Pixel $bitmap ($x - 1) $y $dark
        Set-Pixel $bitmap $x $y $mid
        Set-Pixel $bitmap ($x + 1) $y $light
    }
}

function Draw-DiagonalLeaf($bitmap, [int]$startX, [int]$startY, [int]$length, [int]$dir, $dark, $mid, $light) {
    for ($step = 0; $step -lt $length; $step++) {
        $x = $startX + ($step * $dir)
        $y = $startY - $step
        Set-Pixel $bitmap $x $y $mid
        Set-Pixel $bitmap ($x - $dir) $y $dark
        Set-Pixel $bitmap $x ($y - 1) $light
    }
}

function Draw-LeafCluster($bitmap, [int]$rootX, [int]$rootY, [int]$length, $dark, $mid, $light) {
    Draw-DiagonalLeaf $bitmap $rootX $rootY $length -1 $dark $mid $light
    Draw-DiagonalLeaf $bitmap $rootX $rootY ($length - 1) 1 $dark $mid $light
}

function Draw-RoundFruit($bitmap, [int]$x, [int]$y, [int]$w, [int]$h, $dark, $mid, $light, $sparkle = $null) {
    for ($ix = 0; $ix -lt $w; $ix++) {
        for ($iy = 0; $iy -lt $h; $iy++) {
            $dx = ($ix - (($w - 1) / 2.0)) / (($w + 1) / 2.0)
            $dy = ($iy - (($h - 1) / 2.0)) / (($h + 1) / 2.0)
            if (($dx * $dx) + ($dy * $dy) -le 1.0) {
                $targetX = $x + $ix
                $targetY = $y + $iy
                if ($ix -eq 0 -or $iy -eq 0 -or $ix -eq ($w - 1) -or $iy -eq ($h - 1)) {
                    Set-Pixel $bitmap $targetX $targetY $dark
                }
                elseif ($ix -le 1 -or $iy -le 1) {
                    Set-Pixel $bitmap $targetX $targetY $light
                }
                else {
                    Set-Pixel $bitmap $targetX $targetY $mid
                }
            }
        }
    }

    if ($sparkle) {
        Set-Pixel $bitmap $sparkle[0] $sparkle[1] $light
    }
}

function Draw-Calyx($bitmap, [int]$x, [int]$y, $dark, $mid) {
    Draw-Pixels $bitmap @(
        (Pt $x $y),
        (Pt ($x - 1) ($y + 1)),
        (Pt $x ($y + 1)),
        (Pt ($x + 1) ($y + 1))
    ) $mid
    Draw-Pixels $bitmap @(
        (Pt ($x - 1) $y),
        (Pt ($x + 1) $y)
    ) $dark
}

function Draw-Bulb($bitmap, [int]$x, [int]$y, $outline, $base, $highlight) {
    Draw-Pixels $bitmap @(
        (Pt $x $y),
        (Pt ($x - 1) ($y + 1)),
        (Pt $x ($y + 1)),
        (Pt ($x + 1) ($y + 1)),
        (Pt ($x - 1) ($y + 2)),
        (Pt $x ($y + 2)),
        (Pt ($x + 1) ($y + 2)),
        (Pt $x ($y + 3))
    ) $base
    Draw-Pixels $bitmap @(
        (Pt ($x - 1) $y),
        (Pt ($x + 1) $y),
        (Pt ($x - 2) ($y + 1)),
        (Pt ($x + 2) ($y + 1)),
        (Pt ($x - 2) ($y + 2)),
        (Pt ($x + 2) ($y + 2))
    ) $outline
    Draw-Pixels $bitmap @(
        (Pt $x ($y + 1)),
        (Pt ($x + 1) ($y + 2))
    ) $highlight
}

function Draw-Rhizome($bitmap, [int]$x, [int]$y, $outline, $base, $highlight) {
    Draw-Pixels $bitmap @(
        (Pt $x ($y + 1)),
        (Pt ($x + 1) $y),
        (Pt ($x + 1) ($y + 1)),
        (Pt ($x + 1) ($y + 2)),
        (Pt ($x + 2) ($y + 1)),
        (Pt ($x + 2) ($y + 2)),
        (Pt ($x + 3) ($y + 2)),
        (Pt ($x + 3) ($y + 3))
    ) $base
    Draw-Pixels $bitmap @(
        (Pt $x $y),
        (Pt ($x + 2) $y),
        (Pt ($x + 3) ($y + 1)),
        (Pt ($x + 4) ($y + 2)),
        (Pt ($x + 2) ($y + 3))
    ) $outline
    Draw-Pixels $bitmap @(
        (Pt ($x + 1) ($y + 1)),
        (Pt ($x + 2) ($y + 2))
    ) $highlight
}

function Draw-CornEar($bitmap, [int]$x, [int]$y, $outline, $kernel, $shine, $huskDark, $huskMid) {
    Draw-Pixels $bitmap @(
        (Pt ($x + 1) $y),
        (Pt ($x + 2) $y),
        (Pt $x ($y + 1)),
        (Pt ($x + 1) ($y + 1)),
        (Pt ($x + 2) ($y + 1)),
        (Pt ($x + 1) ($y + 2)),
        (Pt ($x + 2) ($y + 2)),
        (Pt ($x + 1) ($y + 3))
    ) $kernel
    Draw-Pixels $bitmap @(
        (Pt $x $y),
        (Pt ($x + 3) ($y + 1)),
        (Pt ($x + 3) ($y + 2)),
        (Pt ($x + 2) ($y + 3))
    ) $outline
    Draw-Pixels $bitmap @(
        (Pt ($x + 1) ($y + 1)),
        (Pt ($x + 2) ($y + 2))
    ) $shine
    Draw-Pixels $bitmap @(
        (Pt ($x - 1) ($y + 2)),
        (Pt $x ($y + 2)),
        (Pt ($x + 4) ($y + 2)),
        (Pt ($x + 3) ($y + 3))
    ) $huskDark
    Draw-Pixels $bitmap @(
        (Pt $x ($y + 3)),
        (Pt ($x + 4) ($y + 1))
    ) $huskMid
}

function Save-Texture($bitmap, [string]$path) {
    $directory = Split-Path -Parent $path
    if (-not (Test-Path -LiteralPath $directory)) {
        New-Item -ItemType Directory -Path $directory -Force | Out-Null
    }

    $tempPath = Join-Path ([System.IO.Path]::GetTempPath()) (([System.IO.Path]::GetRandomFileName()) + ".png")

    try {
        $bitmap.Save($tempPath, [System.Drawing.Imaging.ImageFormat]::Png)
        $bytes = [System.IO.File]::ReadAllBytes($tempPath)

        if (Test-Path -LiteralPath $path) {
            Remove-Item -LiteralPath $path -Force
        }

        [System.IO.File]::WriteAllBytes($path, $bytes)
    }
    finally {
        if (Test-Path -LiteralPath $tempPath) {
            Remove-Item -LiteralPath $tempPath -Force
        }

        $bitmap.Dispose()
    }
}

function New-GreenPalette($dark, $mid, $light, $accent = $null) {
    return [pscustomobject]@{
        Dark = $dark
        Mid = $mid
        Light = $light
        Accent = $accent
    }
}

$deepGreen = New-Color 31 78 41
$midGreen = New-Color 72 138 63
$lightGreen = New-Color 140 201 101
$sageGreen = New-Color 117 157 87
$oliveGreen = New-Color 88 114 42
$goldDark = New-Color 189 133 38
$goldMid = New-Color 242 199 78
$goldLight = New-Color 255 238 142
$creamDark = New-Color 170 146 116
$creamMid = New-Color 229 211 186
$creamLight = New-Color 255 243 224
$purpleDark = New-Color 74 34 110
$purpleMid = New-Color 137 63 175
$purpleLight = New-Color 203 133 236
$greenFruitDark = New-Color 44 108 52
$greenFruitMid = New-Color 96 176 84
$greenFruitLight = New-Color 164 229 126
$orangeDark = New-Color 152 82 29
$orangeMid = New-Color 208 134 58
$orangeLight = New-Color 248 193 110
$garlicRoot = New-Color 142 99 68
$seedBrown = New-Color 132 98 56
$seedLight = New-Color 234 212 157
$garlicLeafDark = New-Color 49 83 103
$garlicLeafMid = New-Color 92 136 154
$garlicLeafLight = New-Color 164 196 201
$garlicSkinShadow = New-Color 189 165 194
$cucumberDark = New-Color 34 99 42
$cucumberMid = New-Color 77 154 66
$cucumberLight = New-Color 142 213 96
$eggLeafDark = New-Color 39 84 44
$eggLeafMid = New-Color 78 128 72
$eggLeafLight = New-Color 124 177 92

function New-StageTexture($drawAction) {
    $bitmap = New-Canvas
    & $drawAction $bitmap
    return $bitmap
}

function Draw-CornStage0($bitmap) {
    Draw-Column $bitmap 8 11 13 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 12 2 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 12 2 1 $deepGreen $midGreen $lightGreen
}

function Draw-CornStage4($bitmap) {
    Draw-Column $bitmap 8 5 13 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 11 4 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 10 5 1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 8 4 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 7 4 1 $deepGreen $midGreen $lightGreen
}

function Draw-CornStage5($bitmap) {
    Draw-CornStage4 $bitmap
    Draw-CornEar $bitmap 9 8 $goldDark $goldMid $goldLight $oliveGreen $lightGreen
    Draw-Pixels $bitmap @(@(8,4), @(7,3), @(9,3)) $goldLight
}

function Draw-CornStage6($bitmap) {
    Draw-Column $bitmap 8 3 13 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 11 5 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 10 5 1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 8 5 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 7 5 1 $deepGreen $midGreen $lightGreen
    Draw-CornEar $bitmap 9 7 $goldDark $goldMid $goldLight $oliveGreen $lightGreen
    Draw-Pixels $bitmap @(@(8,2), @(7,1), @(9,1), @(8,1)) $goldLight
}

function Draw-CornStage7($bitmap) {
    Draw-CornStage6 $bitmap
    Draw-CornEar $bitmap 4 8 $goldDark $goldMid $goldLight $oliveGreen $lightGreen
    Draw-Pixels $bitmap @(@(8,0), @(7,1), @(9,1), @(6,2), @(10,2)) $goldLight
}

function Draw-CornWild($bitmap) {
    Draw-Column $bitmap 8 5 13 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 11 5 -1 $deepGreen $midGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 10 5 1 $deepGreen $midGreen $lightGreen
    Draw-CornEar $bitmap 9 8 $goldDark $goldMid $goldLight $oliveGreen $lightGreen
    Draw-Pixels $bitmap @(@(6,4), @(8,3), @(10,4)) $goldLight
}

function Draw-CornItem($bitmap) {
    Draw-CornEar $bitmap 5 5 $goldDark $goldMid $goldLight $oliveGreen $lightGreen
    Draw-Pixels $bitmap @(@(4,10), @(5,11), @(9,4)) $oliveGreen
}

function Draw-CornSeeds($bitmap) {
    Draw-RoundFruit $bitmap 4 8 3 3 $goldDark $goldMid $goldLight @(5,8)
    Draw-RoundFruit $bitmap 8 6 3 3 $goldDark $goldMid $goldLight @(9,6)
    Draw-RoundFruit $bitmap 9 10 2 2 $goldDark $goldMid $goldLight @(9,10)
}

function Draw-GarlicStage0($bitmap) {
    Draw-Column $bitmap 8 10 13 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 3 -1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 3 1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
}

function Draw-GarlicStage4($bitmap) {
    Draw-Column $bitmap 8 7 13 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 5 -1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 11 5 1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 9 4 -1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
}

function Draw-GarlicStage5($bitmap) {
    Draw-GarlicStage4 $bitmap
    Draw-Bulb $bitmap 8 10 $garlicSkinShadow $creamMid $creamLight
    Draw-Pixels $bitmap @(@(7,14), @(8,15), @(9,14)) $garlicRoot
}

function Draw-GarlicStage6($bitmap) {
    Draw-GarlicStage5 $bitmap
    Draw-Column $bitmap 9 6 12 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 9 10 4 1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-Pixels $bitmap @(@(8,9), @(9,10), @(7,10)) $creamLight
    Draw-Pixels $bitmap @(@(6,14), @(10,14)) $garlicRoot
}

function Draw-GarlicStage7($bitmap) {
    Draw-GarlicStage6 $bitmap
    Draw-DiagonalLeaf $bitmap 8 7 4 -1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 9 6 4 1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-Pixels $bitmap @(@(7,9), @(8,8), @(9,9)) $creamLight
}

function Draw-GarlicWild($bitmap) {
    Draw-Column $bitmap 8 8 13 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 4 -1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-DiagonalLeaf $bitmap 8 11 3 1 $garlicLeafDark $garlicLeafMid $garlicLeafLight
    Draw-Bulb $bitmap 6 10 $garlicSkinShadow $creamMid $creamLight
    Draw-Pixels $bitmap @(@(5,14), @(6,15), @(7,14)) $garlicRoot
}

function Draw-GarlicItem($bitmap) {
    Draw-Bulb $bitmap 8 5 $garlicSkinShadow $creamMid $creamLight
    Draw-Pixels $bitmap @(@(8,4), @(8,3), @(7,3), @(9,3), @(6,2), @(10,2)) $garlicLeafMid
    Draw-Pixels $bitmap @(@(7,9), @(8,10), @(9,9)) $garlicRoot
}

function Draw-GarlicSeeds($bitmap) {
    Draw-Bulb $bitmap 5 8 $garlicSkinShadow $creamMid $creamLight
    Draw-Pixels $bitmap @(@(4,12), @(5,13)) $garlicRoot
    Draw-Pixels $bitmap @(@(9,8), @(10,9), @(9,10), @(8,10)) $creamMid
    Draw-Pixels $bitmap @(@(9,9)) $creamLight
    Draw-Pixels $bitmap @(@(10,8), @(11,9)) $garlicSkinShadow
}

function Draw-GingerStage0($bitmap) {
    Draw-Column $bitmap 7 10 13 $oliveGreen $sageGreen $lightGreen
    Draw-Column $bitmap 9 9 13 $oliveGreen $sageGreen $lightGreen
    Set-Pixel $bitmap 8 13 $orangeLight
}

function Draw-GingerStage4($bitmap) {
    Draw-Column $bitmap 6 6 13 $oliveGreen $sageGreen $lightGreen
    Draw-Column $bitmap 8 5 13 $oliveGreen $sageGreen $lightGreen
    Draw-Column $bitmap 10 6 13 $oliveGreen $sageGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 9 3 -1 $oliveGreen $sageGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 8 8 3 1 $oliveGreen $sageGreen $lightGreen
}

function Draw-GingerStage5($bitmap) {
    Draw-GingerStage4 $bitmap
    Draw-Rhizome $bitmap 6 10 $orangeDark $orangeMid $orangeLight
}

function Draw-GingerStage6($bitmap) {
    Draw-GingerStage5 $bitmap
    Draw-DiagonalLeaf $bitmap 6 8 4 -1 $oliveGreen $sageGreen $lightGreen
    Draw-DiagonalLeaf $bitmap 10 8 4 1 $oliveGreen $sageGreen $lightGreen
    Set-Pixel $bitmap 10 12 $orangeLight
}

function Draw-GingerStage7($bitmap) {
    Draw-GingerStage6 $bitmap
    Draw-Column $bitmap 8 3 13 $oliveGreen $sageGreen $lightGreen
    Draw-Pixels $bitmap @(@(8,2), @(7,2), @(9,2)) $lightGreen
    Draw-Rhizome $bitmap 5 10 $orangeDark $orangeMid $orangeLight
}

function Draw-GingerWild($bitmap) {
    Draw-GingerStage6 $bitmap
    Draw-Rhizome $bitmap 8 10 $orangeDark $orangeMid $orangeLight
}

function Draw-GingerItem($bitmap) {
    Draw-Rhizome $bitmap 5 6 $orangeDark $orangeMid $orangeLight
    Draw-Pixels $bitmap @(@(6,5), @(8,5), @(9,6)) $orangeLight
}

function Draw-GingerSeeds($bitmap) {
    Draw-Rhizome $bitmap 6 8 $orangeDark $orangeMid $orangeLight
}

function Draw-CucumberStage0($bitmap) {
    Draw-Column $bitmap 8 12 13 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 12 2 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 12 2 1 $cucumberDark $cucumberMid $cucumberLight
}

function Draw-CucumberStage4($bitmap) {
    Draw-Column $bitmap 8 8 13 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 11 3 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 10 3 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 8 2 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-Pixels $bitmap @(@(9,7), @(10,7), @(10,8)) $cucumberLight
}

function Draw-CucumberStage5($bitmap) {
    Draw-CucumberStage4 $bitmap
    Draw-DiagonalLeaf $bitmap 8 9 2 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-RoundFruit $bitmap 10 9 2 3 $greenFruitDark $greenFruitMid $greenFruitLight @(10,9)
    Draw-Pixels $bitmap @(@(8,6), @(7,7), @(9,7)) $goldLight
}

function Draw-CucumberStage6($bitmap) {
    Draw-Column $bitmap 8 7 13 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 11 4 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 10 4 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 8 3 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 8 3 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-RoundFruit $bitmap 10 8 2 5 $greenFruitDark $greenFruitMid $greenFruitLight @(10,8)
    Draw-Pixels $bitmap @(@(5,8), @(4,8), @(4,9), @(5,10)) $cucumberLight
    Draw-Pixels $bitmap @(@(8,6), @(7,7), @(9,7)) $goldLight
}

function Draw-CucumberStage7($bitmap) {
    Draw-Column $bitmap 8 6 13 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 11 5 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 10 5 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 8 4 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 8 4 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-RoundFruit $bitmap 10 7 2 6 $greenFruitDark $greenFruitMid $greenFruitLight @(10,7)
    Draw-Pixels $bitmap @(@(5,7), @(4,7), @(4,8), @(5,9)) $cucumberLight
    Draw-Pixels $bitmap @(@(9,5), @(10,4), @(8,6)) $goldLight
}

function Draw-CucumberWild($bitmap) {
    Draw-Column $bitmap 8 9 13 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 12 4 -1 $cucumberDark $cucumberMid $cucumberLight
    Draw-DiagonalLeaf $bitmap 8 11 3 1 $cucumberDark $cucumberMid $cucumberLight
    Draw-RoundFruit $bitmap 9 9 2 4 $greenFruitDark $greenFruitMid $greenFruitLight @(9,9)
    Draw-Pixels $bitmap @(@(6,9), @(5,9), @(5,10), @(6,11)) $cucumberLight
}

function Draw-CucumberItem($bitmap) {
    Draw-RoundFruit $bitmap 5 5 5 8 $greenFruitDark $greenFruitMid $greenFruitLight @(6,6)
    Draw-Pixels $bitmap @(@(9,4), @(10,5), @(4,11)) $cucumberDark
}

function Draw-CucumberSeeds($bitmap) {
    Draw-RoundFruit $bitmap 4 7 2 3 $seedBrown $seedLight $creamLight @(4,7)
    Draw-RoundFruit $bitmap 8 6 2 3 $seedBrown $seedLight $creamLight @(8,6)
    Draw-RoundFruit $bitmap 10 10 2 3 $seedBrown $seedLight $creamLight @(10,10)
}

function Draw-EggplantStage0($bitmap) {
    Draw-Column $bitmap 8 12 13 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 2 -1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 12 2 1 $eggLeafDark $eggLeafMid $eggLeafLight
}

function Draw-EggplantStage4($bitmap) {
    Draw-Column $bitmap 8 9 13 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-LeafCluster $bitmap 8 11 4 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 9 3 -1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 9 3 1 $eggLeafDark $eggLeafMid $eggLeafLight
}

function Draw-EggplantStage5($bitmap) {
    Draw-EggplantStage4 $bitmap
    Draw-Calyx $bitmap 10 9 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 9 10 3 4 $purpleDark $purpleMid $purpleLight @(10,11)
}

function Draw-EggplantStage6($bitmap) {
    Draw-Column $bitmap 8 8 13 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-LeafCluster $bitmap 8 11 5 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 9 4 -1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 9 4 1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-Calyx $bitmap 10 8 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 9 9 3 5 $purpleDark $purpleMid $purpleLight @(10,10)
    Draw-Calyx $bitmap 5 10 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 4 11 2 4 $purpleDark $purpleMid $purpleLight @(5,12)
}

function Draw-EggplantStage7($bitmap) {
    Draw-Column $bitmap 8 7 13 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-LeafCluster $bitmap 8 11 5 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 8 4 -1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-DiagonalLeaf $bitmap 8 8 4 1 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-Calyx $bitmap 5 8 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 4 9 3 5 $purpleDark $purpleMid $purpleLight @(5,10)
    Draw-Calyx $bitmap 10 8 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 9 9 3 5 $purpleDark $purpleMid $purpleLight @(10,10)
}

function Draw-EggplantWild($bitmap) {
    Draw-Column $bitmap 8 9 13 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-LeafCluster $bitmap 8 12 4 $eggLeafDark $eggLeafMid $eggLeafLight
    Draw-Calyx $bitmap 9 10 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 8 11 2 4 $purpleDark $purpleMid $purpleLight @(9,12)
}

function Draw-EggplantItem($bitmap) {
    Draw-Calyx $bitmap 9 4 $oliveGreen $eggLeafMid
    Draw-RoundFruit $bitmap 6 5 4 8 $purpleDark $purpleMid $purpleLight @(7,6)
    Draw-Pixels $bitmap @(@(10,6), @(11,7), @(11,8)) $purpleDark
}

function Draw-EggplantSeeds($bitmap) {
    Draw-RoundFruit $bitmap 4 8 2 2 $seedBrown $seedLight $creamLight @(4,8)
    Draw-RoundFruit $bitmap 7 6 2 2 $seedBrown $seedLight $creamLight @(7,6)
    Draw-RoundFruit $bitmap 10 9 2 2 $seedBrown $seedLight $creamLight @(10,9)
}

$textures = @(
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_corn_crop_stage0"; Draw = ${function:Draw-CornStage0} },
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_corn_crop_stage4"; Draw = ${function:Draw-CornStage4} },
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_corn_crop_stage5"; Draw = ${function:Draw-CornStage5} },
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_corn_crop_stage6"; Draw = ${function:Draw-CornStage6} },
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_corn_crop_stage7"; Draw = ${function:Draw-CornStage7} },
    [pscustomobject]@{ Crop = "corn"; Kind = "block"; Name = "bobixuan_wild_corn"; Draw = ${function:Draw-CornWild} },
    [pscustomobject]@{ Crop = "corn"; Kind = "item"; Name = "bobixuan_corn"; Draw = ${function:Draw-CornItem} },
    [pscustomobject]@{ Crop = "corn"; Kind = "item"; Name = "bobixuan_corn_seeds"; Draw = ${function:Draw-CornSeeds} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_garlic_crop_stage0"; Draw = ${function:Draw-GarlicStage0} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_garlic_crop_stage4"; Draw = ${function:Draw-GarlicStage4} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_garlic_crop_stage5"; Draw = ${function:Draw-GarlicStage5} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_garlic_crop_stage6"; Draw = ${function:Draw-GarlicStage6} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_garlic_crop_stage7"; Draw = ${function:Draw-GarlicStage7} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "block"; Name = "bobixuan_wild_garlic"; Draw = ${function:Draw-GarlicWild} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "item"; Name = "bobixuan_garlic"; Draw = ${function:Draw-GarlicItem} },
    [pscustomobject]@{ Crop = "garlic"; Kind = "item"; Name = "bobixuan_garlic_seeds"; Draw = ${function:Draw-GarlicSeeds} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_ginger_crop_stage0"; Draw = ${function:Draw-GingerStage0} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_ginger_crop_stage4"; Draw = ${function:Draw-GingerStage4} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_ginger_crop_stage5"; Draw = ${function:Draw-GingerStage5} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_ginger_crop_stage6"; Draw = ${function:Draw-GingerStage6} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_ginger_crop_stage7"; Draw = ${function:Draw-GingerStage7} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "block"; Name = "bobixuan_wild_ginger"; Draw = ${function:Draw-GingerWild} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "item"; Name = "bobixuan_ginger"; Draw = ${function:Draw-GingerItem} },
    [pscustomobject]@{ Crop = "ginger"; Kind = "item"; Name = "bobixuan_ginger_seeds"; Draw = ${function:Draw-GingerSeeds} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_cucumber_crop_stage0"; Draw = ${function:Draw-CucumberStage0} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_cucumber_crop_stage4"; Draw = ${function:Draw-CucumberStage4} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_cucumber_crop_stage5"; Draw = ${function:Draw-CucumberStage5} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_cucumber_crop_stage6"; Draw = ${function:Draw-CucumberStage6} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_cucumber_crop_stage7"; Draw = ${function:Draw-CucumberStage7} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "block"; Name = "bobixuan_wild_cucumber"; Draw = ${function:Draw-CucumberWild} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "item"; Name = "bobixuan_cucumber"; Draw = ${function:Draw-CucumberItem} },
    [pscustomobject]@{ Crop = "cucumber"; Kind = "item"; Name = "bobixuan_cucumber_seeds"; Draw = ${function:Draw-CucumberSeeds} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_eggplant_crop_stage0"; Draw = ${function:Draw-EggplantStage0} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_eggplant_crop_stage4"; Draw = ${function:Draw-EggplantStage4} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_eggplant_crop_stage5"; Draw = ${function:Draw-EggplantStage5} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_eggplant_crop_stage6"; Draw = ${function:Draw-EggplantStage6} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_eggplant_crop_stage7"; Draw = ${function:Draw-EggplantStage7} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "block"; Name = "bobixuan_wild_eggplant"; Draw = ${function:Draw-EggplantWild} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "item"; Name = "bobixuan_eggplant"; Draw = ${function:Draw-EggplantItem} },
    [pscustomobject]@{ Crop = "eggplant"; Kind = "item"; Name = "bobixuan_eggplant_seeds"; Draw = ${function:Draw-EggplantSeeds} }
)

$platforms = @("fabric", "forge")

foreach ($texture in $textures) {
    $bitmap = New-StageTexture $texture.Draw
    $fabricPath = Join-Path $ProjectRoot ("fabric\src\main\resources\assets\bobidelight\textures\{0}\{1}.png" -f $texture.Kind, $texture.Name)
    Save-Texture $bitmap $fabricPath

    foreach ($platform in $platforms | Where-Object { $_ -ne "fabric" }) {
        $targetPath = Join-Path $ProjectRoot ("{0}\src\main\resources\assets\bobidelight\textures\{1}\{2}.png" -f $platform, $texture.Kind, $texture.Name)
        $directory = Split-Path -Parent $targetPath
        if (-not (Test-Path -LiteralPath $directory)) {
            New-Item -ItemType Directory -Path $directory -Force | Out-Null
        }
        Copy-Item -LiteralPath $fabricPath -Destination $targetPath -Force
    }
}

Write-Output ("Generated {0} textures for corn, garlic, ginger, cucumber, and eggplant across Fabric/Forge." -f $textures.Count)