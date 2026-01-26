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
import com.ultraluv.arbiter.loadIcon

@Composable
fun Mine(
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
                .background(Color.Red)
                .onePointClick(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = loadIcon(AppIcon.Bomb), contentDescription = "Bomb")
        }
    }

//    FloatingActionButton(
//        onClick = {},
//        modifier = Modifier
//            .size(cellSizeDp)
//            .padding(2.dp)
//            .onePointClick(
//                onClick = onClick
//            ),
//        containerColor = Color.Red,
//    ){
//        Icon(painter = loadIcon(AppIcon.Bomb), contentDescription = "Bomb")
//    }
}