package com.snaply.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.snaply.designsystem.theme.SnaplyColors
import com.snaply.designsystem.theme.SnaplyCorners
import com.snaply.designsystem.theme.SnaplyDimensions
import com.snaply.designsystem.theme.SnaplySpacing

enum class SnaplyButtonStyle { Primary, Secondary }

@Composable
fun SnaplyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: SnaplyButtonStyle = SnaplyButtonStyle.Primary,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val shape = if (style == SnaplyButtonStyle.Primary) SnaplyCorners.primaryButton else SnaplyCorners.secondaryButton
    val enabledColor = if (style == SnaplyButtonStyle.Primary) SnaplyColors.Lime else SnaplyColors.Purple
    val containerColor = when {
        !enabled -> SnaplyColors.Border
        pressed && style == SnaplyButtonStyle.Primary -> Color(0xFFC9E23F)
        pressed -> Color(0xFF4829B0)
        else -> enabledColor
    }
    val contentColor = if (!enabled) SnaplyColors.Muted else if (style == SnaplyButtonStyle.Primary) SnaplyColors.Ink else SnaplyColors.White
    Row(
        modifier = modifier
            .defaultMinSize(minWidth = SnaplyDimensions.minTouchTarget, minHeight = SnaplyDimensions.buttonHeight)
            .drawBehind {
                val shadowRadius = 16.dp.toPx()
                if (enabled) {
                    drawRoundRect(
                        color = SnaplyColors.Ink,
                        topLeft = Offset(SnaplyDimensions.hardShadowX.toPx(), SnaplyDimensions.hardShadowY.toPx()),
                        cornerRadius = CornerRadius(shadowRadius),
                    )
                }
                drawRoundRect(color = containerColor, cornerRadius = CornerRadius(shadowRadius))
                if (style == SnaplyButtonStyle.Primary) {
                    drawRoundRect(
                        color = if (enabled) SnaplyColors.Ink else SnaplyColors.Muted,
                        cornerRadius = CornerRadius(shadowRadius),
                        style = Stroke(width = SnaplyDimensions.primaryButtonBorder.toPx()),
                    )
                }
            }
            .clip(shape)
            .clickable(interactionSource = interactionSource, indication = null, enabled = enabled, onClick = onClick)
            .padding(horizontal = SnaplySpacing.lg, vertical = SnaplySpacing.md),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { leadingIcon() }
            Spacer(Modifier.width(SnaplySpacing.sm))
        }
        Text(text = text, style = MaterialTheme.typography.labelLarge, color = contentColor)
    }
}

enum class SnaplyInputType { Text, Email, Password, Bio }

@Composable
fun SnaplyInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    placeholder: String? = null,
    type: SnaplyInputType = SnaplyInputType.Text,
    compact: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = type != SnaplyInputType.Bio,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val keyboardType = when (type) {
        SnaplyInputType.Email -> KeyboardType.Email
        SnaplyInputType.Password -> KeyboardType.Password
        else -> KeyboardType.Text
    }
    val visualTransformation = if (type == SnaplyInputType.Password && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(SnaplySpacing.xxs)) {
        Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = if (compact) SnaplyDimensions.compactInputHeight else SnaplyDimensions.inputHeight),
            placeholder = placeholder?.let { { Text(it) } },
            supportingText = supportingText?.let { { Text(it) } },
            trailingIcon = if (type == SnaplyInputType.Password) {
                {
                    TextButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.semantics {
                            contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                        },
                    ) {
                        Text(if (passwordVisible) "Ocultar" else "Mostrar")
                    }
                }
            } else null,
            singleLine = singleLine,
            minLines = if (type == SnaplyInputType.Bio && !singleLine) 3 else 1,
            enabled = enabled,
            shape = if (compact) SnaplyCorners.compactInput else SnaplyCorners.input,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun SnaplyIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(SnaplyDimensions.minTouchTarget)
            .clip(CircleShape)
            .clickable(enabled = enabled, onClickLabel = contentDescription, onClick = onClick)
            .semantics { this.contentDescription = contentDescription },
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) { icon() }
    }
}

@Composable
fun SnaplyPreferenceRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = SnaplyDimensions.minTouchTarget)
            .clickable(enabled = enabled) { onCheckedChange(!checked) }
            .padding(vertical = SnaplySpacing.xs),
        horizontalArrangement = Arrangement.spacedBy(SnaplySpacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(SnaplySpacing.xxs)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
            if (description != null) {
                Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange, enabled = enabled)
    }
}

@Composable
fun SnaplyAvatarArt(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun SnaplyAvatar(
    painter: Painter,
    contentDescription: String?,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    SnaplyAvatarArt(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(SnaplyColors.Mint),
    )
}

@Composable
fun SnaplyAvatarOption(
    painter: Painter,
    contentDescription: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(SnaplyDimensions.avatarOption)
            .clip(CircleShape)
            .clickable(onClickLabel = contentDescription, onClick = onClick)
            .border(
                BorderStroke(if (selected) 3.dp else SnaplyDimensions.border, if (selected) SnaplyColors.Purple else SnaplyColors.Border),
                CircleShape,
            )
            .padding(SnaplySpacing.xxs),
    ) {
        SnaplyAvatar(painter, contentDescription, size = SnaplyDimensions.avatarOption - SnaplySpacing.xs)
    }
}

@Composable
fun SnaplyBrandSymbol(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    Image(painter, contentDescription, modifier, contentScale = ContentScale.Fit)
}

@Composable
fun SnaplyBrandSignature(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    Image(painter, contentDescription, modifier, contentScale = ContentScale.Fit)
}