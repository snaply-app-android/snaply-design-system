package com.snaply.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.snaply.designsystem.theme.SnaplyColors
import com.snaply.designsystem.theme.SnaplyDimensions
import com.snaply.designsystem.theme.SnaplySpacing

@Composable
fun SnaplyHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    backIcon: (@Composable () -> Unit)? = null,
    backDescription: String = "Voltar",
    action: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .heightIn(min = SnaplyDimensions.headerMinHeight)
            .padding(horizontal = SnaplySpacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            if (onBack != null && backIcon != null) {
                SnaplyIconButton(onClick = onBack, contentDescription = backDescription, icon = backIcon)
            }
        }
        Box(Modifier.weight(3f), contentAlignment = Alignment.Center) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) { action?.invoke() }
    }
}

enum class SnaplyTab(val label: String) {
    Home("Home"),
    Challenges("Desafios"),
    Ranking("Ranking"),
    Profile("Perfil"),
}

@Composable
fun SnaplyTabItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .heightIn(min = SnaplyDimensions.minTouchTarget)
            .clip(CircleShape)
            .clickable(role = Role.Tab, onClick = onClick)
            .semantics { this.selected = selected }
            .padding(horizontal = SnaplySpacing.xs, vertical = SnaplySpacing.xxs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(if (selected) SnaplyColors.Lime else androidx.compose.ui.graphics.Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun SnaplyBottomBar(
    activeTab: SnaplyTab,
    onTabSelected: (SnaplyTab) -> Unit,
    iconForTab: @Composable (SnaplyTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth().navigationBarsPadding(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SnaplyDimensions.bottomBarContentHeight)
                .padding(horizontal = SnaplySpacing.xs, vertical = SnaplySpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SnaplyTab.entries.forEach { tab ->
                SnaplyTabItem(
                    label = tab.label,
                    selected = tab == activeTab,
                    onClick = { onTabSelected(tab) },
                    icon = { iconForTab(tab) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
fun SnaplySystemInsets(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { content() }
}