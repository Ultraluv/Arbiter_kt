package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.loadIcon

@Composable
fun ZeroCell(
    cellSizeDp: Dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(cellSizeDp)
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
            .onePointClick(
                isEnableHaptic = false,
                onClick = onClick
            )
    )
}