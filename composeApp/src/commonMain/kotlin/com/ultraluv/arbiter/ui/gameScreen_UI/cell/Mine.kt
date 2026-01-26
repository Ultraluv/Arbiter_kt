package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.MyIconPack
import com.ultraluv.arbiter.canvasIconPath.FlagCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.MineCanvasIcon
import com.ultraluv.arbiter.loadIcon
import com.ultraluv.arbiter.myiconpack.Bomb24dp

@Composable
fun Mine(
    cellSizeDp: Dp,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(cellSizeDp)
            .padding(cellSizeDp * 0.02f),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.4.dp,
            pressedElevation = 0.2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .onePointClick(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(0.2.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ){
                MineCanvasIcon(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}