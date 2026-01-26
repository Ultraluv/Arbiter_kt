package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.ultraluv.arbiter.loadIcon
import com.ultraluv.arbiter.myiconpack.Bomb24dp
import com.ultraluv.arbiter.myiconpack.Circle24dp

@Composable
fun UnFlaggedCell(
    cellSizeDp: Dp,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(cellSizeDp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 1.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .onePointClick(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
//            Icon(painter = loadIcon(AppIcon.Zero), contentDescription = "Zero")

            Icon(
                imageVector = MyIconPack.Circle24dp,
                modifier = Modifier
                    .size(cellSizeDp - 2.dp),
                contentDescription = "Flag"
            )
        }
    }
}