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
    }
}