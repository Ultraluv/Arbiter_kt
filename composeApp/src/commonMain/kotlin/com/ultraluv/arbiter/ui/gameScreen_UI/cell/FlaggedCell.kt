package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FlaggedCell(
    cellSizeDp: Dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(cellSizeDp)
            .padding(2.dp)
            .background(Color.Yellow)
            .onePointClick(
                onClick = onClick
            )
    )
}