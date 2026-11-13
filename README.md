# snaply-design-system

Biblioteca de componentes Snaply para Jetpack Compose.

## Theme

Use a família Inter incluída e aplique o tema na raiz da interface:

```kotlin
SnaplyTheme(inter = InterFontFamily) {
	// Conteúdo Compose do aplicativo
}
```

O tema oferece `SnaplyColors`, `SnaplySpacing`, `SnaplyDimensions`, `SnaplyCorners`, `SnaplyShapes` e `snaplyTypography`. A fonte Inter variável e sua licença OFL estão incluídas no módulo.

## Componentes

Os componentes estão nos pacotes `com.snaply.designsystem.component` e `com.snaply.designsystem.theme`. Botões, campos, preferências, navegação, chips, progresso, cards, estado vazio e indicadores são controlados por parâmetros; navegação e valores de usuário permanecem sob responsabilidade da tela.

Ícones, ilustrações, as 20 artes de avatar e os recursos de marca não estavam presentes neste repositório. Os componentes `SnaplyAvatar`, `SnaplyAvatarArt`, `SnaplyBrandSymbol` e `SnaplyBrandSignature` recebem `Painter` do aplicativo, sem redesenhar nem substituir esses recursos. Slots de ícone recebem conteúdo Compose do consumidor.

O `SnaplyCard` centraliza variações visuais reutilizáveis. A correspondência detalhada com os 24 modelos do protótipo depende dos modelos/telas, que não estão incluídos neste módulo.

As amostras de Preview estão em `com.snaply.designsystem.preview.SnaplyComponentPreviews`.