package com.ultraluv.arbiter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

enum class AppIcon {
    Back,
    Play,
    RePlay,
    Start,
    Setting,
    Flag,
    Click,
}
@Composable
expect fun loadIcon(icon: AppIcon): Painter