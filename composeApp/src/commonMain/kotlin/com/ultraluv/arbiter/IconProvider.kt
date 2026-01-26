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
    Zero,
    Bomb,
    Counter_1,
    Counter_2,
    Counter_3,
    Counter_4,
    Counter_5,
    Counter_6,
    Counter_7,
    Counter_8,
}
@Composable
expect fun loadIcon(icon: AppIcon): Painter