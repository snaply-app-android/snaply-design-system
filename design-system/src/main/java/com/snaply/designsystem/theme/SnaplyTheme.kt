package com.snaply.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily

@Composable
fun SnaplyTheme(
    inter: FontFamily,
    content: @Composable () -> Unit,
) {
    val typography = remember(inter) { snaplyTypography(inter) }
    MaterialTheme(
        colorScheme = SnaplyLightColors,
        typography = typography,
        shapes = SnaplyShapes,
        content = content,
    )
}