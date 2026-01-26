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
        AppIcon.Zero -> painterResource("circle_24dp.svg")
        AppIcon.Bomb -> painterResource("bomb_24dp.svg")
        AppIcon.Counter_1 -> painterResource("counter_1_24dp.svg")
        AppIcon.Counter_2 -> painterResource("counter_2_24dp.svg")
        AppIcon.Counter_3 -> painterResource("counter_3_24dp.svg")
        AppIcon.Counter_4 -> painterResource("counter_4_24dp.svg")
        AppIcon.Counter_5 -> painterResource("counter_5_24dp.svg")
        AppIcon.Counter_6 -> painterResource("counter_6_24dp.svg")
        AppIcon.Counter_7 -> painterResource("counter_7_24dp.svg")
        AppIcon.Counter_8 -> painterResource("counter_8_24dp.svg")
    }
}