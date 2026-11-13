package com.snaply.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SnaplyColors {
    val Lime = Color(0xFFDAF64B)
    val Ink = Color(0xFF24232C)
    val Cream = Color(0xFFF7F6EE)
    val White = Color(0xFFFFFFFF)
    val Purple = Color(0xFF5A36CF)
    val Muted = Color(0xFF696676)
    val Border = Color(0xFFD5D4D8)
    val Pink = Color(0xFFF5B4DA)
    val Mint = Color(0xFFCEF2ED)
}

val SnaplyLightColors = lightColorScheme(
    primary = SnaplyColors.Lime,
    onPrimary = SnaplyColors.Ink,
    secondary = SnaplyColors.Purple,
    onSecondary = SnaplyColors.White,
    background = SnaplyColors.Cream,
    onBackground = SnaplyColors.Ink,
    surface = SnaplyColors.White,
    onSurface = SnaplyColors.Ink,
    surfaceVariant = SnaplyColors.Cream,
    onSurfaceVariant = SnaplyColors.Muted,
    outline = SnaplyColors.Border,
    outlineVariant = SnaplyColors.Border,
    surfaceTint = Color.Transparent,
)

object SnaplySpacing {
    val none = 0.dp
    val xxs = 4.dp
    val xs = 8.dp
    val sm = 12.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 32.dp
    val xxl = 48.dp
    val xxxl = 56.dp
}

object SnaplyDimensions {
    val screenPadding = 24.dp
    val buttonHeight = 56.dp
    val inputHeight = 56.dp
    val compactInputHeight = 44.dp
    val headerMinHeight = 44.dp
    val bottomBarContentHeight = 70.dp
    val minTouchTarget = 48.dp
    val avatarSmall = 40.dp
    val avatarMedium = 44.dp
    val avatarLarge = 66.dp
    val avatarOption = 72.dp
    val border = 1.dp
    val primaryButtonBorder = 1.25.dp
    val hardShadowX = 2.dp
    val hardShadowY = 3.dp
}

object SnaplyCorners {
    val primaryButton = RoundedCornerShape(16.dp)
    val secondaryButton = RoundedCornerShape(18.dp)
    val input = RoundedCornerShape(18.dp)
    val compactInput = RoundedCornerShape(14.dp)
    val card = RoundedCornerShape(18.dp)
    val featuredCard = RoundedCornerShape(20.dp)
}

val SnaplyShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(20.dp),
)