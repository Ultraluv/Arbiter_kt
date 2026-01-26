package com.ultraluv.arbiter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun loadIcon(icon: AppIcon): Painter {
    return when(icon) {
        AppIcon.Back -> painterResource(R.drawable.arrow_back_24dp)
        AppIcon.Play -> painterResource(R.drawable.play_circle_24dp)
        AppIcon.RePlay -> painterResource(R.drawable.replay_24dp)
        AppIcon.Start -> painterResource(R.drawable.play_arrow_24dp)
        AppIcon.Setting -> painterResource(R.drawable.settings_24dp)
        AppIcon.Flag -> painterResource(R.drawable.flag_24dp)
        AppIcon.Click -> painterResource(R.drawable.ads_click_24dp)
        AppIcon.Zero -> painterResource(R.drawable.circle_24dp)
        AppIcon.Bomb -> painterResource(R.drawable.bomb_24dp)
        AppIcon.Counter_1 -> painterResource(R.drawable.counter_1_24dp)
        AppIcon.Counter_2 -> painterResource(R.drawable.counter_2_24dp)
        AppIcon.Counter_3 -> painterResource(R.drawable.counter_3_24dp)
        AppIcon.Counter_4 -> painterResource(R.drawable.counter_4_24dp)
        AppIcon.Counter_5 -> painterResource(R.drawable.counter_5_24dp)
        AppIcon.Counter_6 -> painterResource(R.drawable.counter_6_24dp)
        AppIcon.Counter_7 -> painterResource(R.drawable.counter_7_24dp)
        AppIcon.Counter_8 -> painterResource(R.drawable.counter_8_24dp)
    }
}