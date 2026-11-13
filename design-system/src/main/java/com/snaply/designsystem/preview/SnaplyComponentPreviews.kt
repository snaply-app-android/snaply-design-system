package com.snaply.designsystem.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snaply.designsystem.component.SnaplyAvatar
import com.snaply.designsystem.component.SnaplyAvatarArt
import com.snaply.designsystem.component.SnaplyAvatarOption
import com.snaply.designsystem.component.SnaplyBrandSymbol
import com.snaply.designsystem.component.SnaplyBottomBar
import com.snaply.designsystem.component.SnaplyBrandSignature
import com.snaply.designsystem.component.SnaplyButton
import com.snaply.designsystem.component.SnaplyButtonStyle
import com.snaply.designsystem.component.SnaplyCard
import com.snaply.designsystem.component.SnaplyCardVariant
import com.snaply.designsystem.component.SnaplyChip
import com.snaply.designsystem.component.SnaplyChipType
import com.snaply.designsystem.component.SnaplyEmptyState
import com.snaply.designsystem.component.SnaplyHeader
import com.snaply.designsystem.component.SnaplyInput
import com.snaply.designsystem.component.SnaplyInputType
import com.snaply.designsystem.component.SnaplyIconButton
import com.snaply.designsystem.component.SnaplyPreferenceRow
import com.snaply.designsystem.component.SnaplyProgress
import com.snaply.designsystem.component.SnaplyStatus
import com.snaply.designsystem.component.SnaplyStatusIndicator
import com.snaply.designsystem.component.SnaplyTab
import com.snaply.designsystem.component.SnaplyTabItem
import com.snaply.designsystem.theme.SnaplyColors
import com.snaply.designsystem.theme.InterFontFamily
import com.snaply.designsystem.theme.SnaplySpacing
import com.snaply.designsystem.theme.SnaplyTheme

@Preview(showBackground = true)
@Composable
private fun SnaplyPersonOnePreview() {
    SnaplyTheme(inter = InterFontFamily) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(SnaplySpacing.lg),
            verticalArrangement = Arrangement.spacedBy(SnaplySpacing.md),
        ) {
            SnaplyButton("Continuar", onClick = {})
            SnaplyButton("Criar conta", onClick = {}, style = SnaplyButtonStyle.Secondary)
            SnaplyButton("Desabilitado", onClick = {}, enabled = false)
            SnaplyInput("", {}, label = "E-mail", type = SnaplyInputType.Email)
            SnaplyInput("", {}, label = "Senha", type = SnaplyInputType.Password, compact = true)
            SnaplyInput("", {}, label = "Bio", type = SnaplyInputType.Bio)
            SnaplyPreferenceRow("Perfil público", checked = true, onCheckedChange = {})
            SnaplyIconButton(onClick = {}, contentDescription = "Settings") {
                Box(Modifier.size(16.dp).background(SnaplyColors.Purple, CircleShape))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(SnaplySpacing.md), verticalAlignment = Alignment.CenterVertically) {
                SnaplyAvatar(ColorPainter(SnaplyColors.Mint), "Avatar de exemplo", 66.dp)
                SnaplyAvatarOption(ColorPainter(SnaplyColors.Pink), "Avatar selecionável", selected = true, onClick = {})
            }
            SnaplyAvatarArt(ColorPainter(SnaplyColors.Pink), "Arte de avatar", Modifier.size(72.dp))
            SnaplyBrandSymbol(ColorPainter(SnaplyColors.Purple), "Brand symbol", Modifier.size(32.dp))
            SnaplyBrandSignature(ColorPainter(SnaplyColors.Purple), "Assinatura da marca", Modifier.size(width = 140.dp, height = 32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnaplyPersonTwoPreview() {
    SnaplyTheme(inter = InterFontFamily) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(SnaplySpacing.md),
            verticalArrangement = Arrangement.spacedBy(SnaplySpacing.md),
        ) {
            SnaplyHeader(title = "Ranking", action = {
                Text("...", modifier = Modifier.padding(SnaplySpacing.xs))
            })
            Row(horizontalArrangement = Arrangement.spacedBy(SnaplySpacing.xs)) {
                SnaplyChip("Todos", selected = true, onClick = {})
                SnaplyChip("Bem-estar", type = SnaplyChipType.Category, onClick = {})
                SnaplyChip("Nível 3", type = SnaplyChipType.Level)
            }
            SnaplyProgress(value = 420f, total = 650f, legend = "420 XP")
            SnaplyCard { Text("Desafio da semana", style = MaterialTheme.typography.titleMedium) }
            SnaplyCard(variant = SnaplyCardVariant.Featured) { Text("Destaque", style = MaterialTheme.typography.titleMedium) }
            SnaplyCard(variant = SnaplyCardVariant.Outlined) { Text("Conquista") }
            SnaplyStatusIndicator(SnaplyStatus.Completed) { Text("Concluido") }
            SnaplyStatusIndicator(SnaplyStatus.Locked) { Text("Bloqueado") }
            SnaplyEmptyState("Sem participantes", "O ranking aparecerá quando houver atividade.")
            SnaplyBottomBar(activeTab = SnaplyTab.Home, onTabSelected = {}, iconForTab = {
                Box(Modifier.size(8.dp).background(Color.DarkGray, CircleShape))
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnaplyNavigationStatesPreview() {
    SnaplyTheme(inter = InterFontFamily) {
        Surface(color = SnaplyColors.Cream) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(SnaplySpacing.md),
                verticalArrangement = Arrangement.spacedBy(SnaplySpacing.sm),
            ) {
                SnaplyTabItem("Home", selected = true, onClick = {}, icon = {
                    Box(Modifier.size(12.dp).background(SnaplyColors.Purple, CircleShape))
                })
                SnaplyTabItem("Ranking", selected = false, onClick = {}, icon = {
                    Box(Modifier.size(12.dp).background(SnaplyColors.Muted, CircleShape))
                })
            }
        }
    }
}