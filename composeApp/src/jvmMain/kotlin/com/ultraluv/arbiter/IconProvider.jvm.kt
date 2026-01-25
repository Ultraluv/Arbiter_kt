package com.ultraluv.arbiter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter

@Composable
actual fun loadIcon(icon: AppIcon): Painter {
    return when(icon) {
        AppIcon.Back -> painterResource("arrow_back_24dp.svg")
        AppIcon.Play -> painterResource("play_circle_24dp.svg")
        AppIcon.RePlay -> painterResource("replay_24.dp.svg")
        AppIcon.Start -> painterResource("play_arrow_24dp.svg")
        AppIcon.Setting -> painterResource("settings_24dp.svg")
        AppIcon.Flag -> painterResource("flag_24dp.svg")
        AppIcon.Click -> painterResource("ads_click_24dp.svg")
    }
}