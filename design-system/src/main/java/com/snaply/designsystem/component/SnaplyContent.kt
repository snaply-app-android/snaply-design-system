package com.snaply.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.snaply.designsystem.theme.SnaplyColors
import com.snaply.designsystem.theme.SnaplyCorners
import com.snaply.designsystem.theme.SnaplyDimensions
import com.snaply.designsystem.theme.SnaplySpacing
import kotlin.math.max
import kotlin.math.min

enum class SnaplyChipType { Category, Filter, Level, Experience }

@Composable
fun SnaplyChip(
    text: String,
    modifier: Modifier = Modifier,
    type: SnaplyChipType = SnaplyChipType.Category,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val backgroundColor = when {
        selected -> SnaplyColors.Purple
        type == SnaplyChipType.Level -> SnaplyColors.Pink
        type == SnaplyChipType.Experience -> SnaplyColors.Mint
        else -> SnaplyColors.White
    }
    val foregroundColor = when {
        selected -> SnaplyColors.White
        type == SnaplyChipType.Level || type == SnaplyChipType.Experience -> SnaplyColors.Ink
        else -> SnaplyColors.Muted
    }
    Surface(
        modifier = modifier,
        onClick = onClick ?: {},
        enabled = onClick != null,
        shape = RoundedCornerShape(50),
        color = backgroundColor,
        contentColor = foregroundColor,
        border = androidx.compose.foundation.BorderStroke(SnaplyDimensions.border, if (selected) SnaplyColors.Purple else SnaplyColors.Border),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = SnaplySpacing.md, vertical = SnaplySpacing.xs),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun SnaplyProgress(
    value: Float,
    total: Float,
    modifier: Modifier = Modifier,
    legend: String? = null,
) {
    val progress = if (total > 0f) (value / total).coerceIn(0f, 1f) else 0f
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(SnaplySpacing.xs)) {
        if (legend != null) {
            Text(legend, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape)
                .background(SnaplyColors.Border)
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo(progress, 0f..1f) },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(SnaplyColors.Purple),
            )
        }
    }
}

enum class SnaplyCardVariant { Standard, Featured, Outlined, Tinted, Glass }

@Composable
fun SnaplyCard(
    modifier: Modifier = Modifier,
    variant: SnaplyCardVariant = SnaplyCardVariant.Standard,
    content: @Composable BoxScope.() -> Unit,
) {
    val shape = if (variant == SnaplyCardVariant.Featured) SnaplyCorners.featuredCard else SnaplyCorners.card
    val color = when (variant) {
        SnaplyCardVariant.Tinted -> SnaplyColors.Mint
        SnaplyCardVariant.Glass -> SnaplyColors.White.copy(alpha = 0.88f)
        else -> SnaplyColors.White
    }
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        border = when (variant) {
            SnaplyCardVariant.Outlined -> androidx.compose.foundation.BorderStroke(SnaplyDimensions.border, SnaplyColors.Border)
            SnaplyCardVariant.Glass -> androidx.compose.foundation.BorderStroke(SnaplyDimensions.border, SnaplyColors.White)
            else -> null
        },
        shadowElevation = if (variant == SnaplyCardVariant.Standard || variant == SnaplyCardVariant.Featured) 2.dp else 0.dp,
    ) {
        Box(Modifier.padding(SnaplySpacing.md), content = content)
    }
}

@Composable
fun SnaplyEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    illustration: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(SnaplySpacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SnaplySpacing.md),
    ) {
        illustration?.invoke()
        Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
        Text(message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
        if (actionLabel != null && onAction != null) {
            SnaplyButton(text = actionLabel, onClick = onAction)
        }
    }
}

enum class SnaplyStatus { Completed, Locked, InProgress }

@Composable
fun SnaplyStatusIndicator(
    status: SnaplyStatus,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val color = when (status) {
        SnaplyStatus.Completed -> SnaplyColors.Lime
        SnaplyStatus.Locked -> SnaplyColors.Border
        SnaplyStatus.InProgress -> SnaplyColors.Purple
    }
    Row(
        modifier = modifier.heightIn(min = SnaplyDimensions.minTouchTarget),
        horizontalArrangement = Arrangement.spacedBy(SnaplySpacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(12.dp).clip(CircleShape).background(color))
        content()
    }
}