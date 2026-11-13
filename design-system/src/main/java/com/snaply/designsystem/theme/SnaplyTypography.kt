package com.snaply.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun snaplyTextStyle(
    family: FontFamily,
    size: Int,
    weight: FontWeight,
) = TextStyle(
    fontFamily = family,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = (size * 1.35f).sp,
    letterSpacing = 0.sp,
)

fun snaplyTypography(inter: FontFamily) = Typography(
    displayLarge = snaplyTextStyle(inter, 54, FontWeight.Bold),
    displayMedium = snaplyTextStyle(inter, 36, FontWeight.Bold),
    displaySmall = snaplyTextStyle(inter, 34, FontWeight.Bold),
    headlineLarge = snaplyTextStyle(inter, 36, FontWeight.Bold),
    headlineMedium = snaplyTextStyle(inter, 34, FontWeight.Bold),
    headlineSmall = snaplyTextStyle(inter, 28, FontWeight.Bold),
    titleLarge = snaplyTextStyle(inter, 26, FontWeight.Bold),
    titleMedium = snaplyTextStyle(inter, 20, FontWeight.Bold),
    titleSmall = snaplyTextStyle(inter, 16, FontWeight.SemiBold),
    bodyLarge = snaplyTextStyle(inter, 15, FontWeight.Normal),
    bodyMedium = snaplyTextStyle(inter, 14, FontWeight.Normal),
    bodySmall = snaplyTextStyle(inter, 12, FontWeight.Normal),
    labelLarge = snaplyTextStyle(inter, 15, FontWeight.SemiBold),
    labelMedium = snaplyTextStyle(inter, 12, FontWeight.Medium),
    labelSmall = snaplyTextStyle(inter, 10, FontWeight.Medium),
)