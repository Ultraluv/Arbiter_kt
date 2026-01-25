package com.ultraluv.arbiter.ui.gameScreen_UI.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.ui.gameScreen_UI.action.CellClickListener
import com.ultraluv.arbiter.ui.gameScreen_UI.cover.Cover
import com.ultraluv.arbiter.ui.gameScreen_UI.cover.CoverState
import com.ultraluv.arbiter.ui.gameScreen_UI.grid.MineGrid
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation.DisplayGridInformation

@Composable
fun Board(
    displayGridInformation: DisplayGridInformation,
    cellClickListener: CellClickListener,
    endPoint: @Composable () -> Unit,
){
    val gridSize = displayGridInformation.gridSize
    val coverState = remember {
        mutableStateOf(
            CoverState(
                scale = 1f,
                translationX = 0f,
                translationY = 0f,
            )
        )
    }
    val cellSizeDp = remember { mutableStateOf(0.dp) }

    Cover(gridSize) { newCoverState, newCellSizeDp ->
        coverState.value = newCoverState
        cellSizeDp.value = newCellSizeDp

        MineGrid(
            modifier = Modifier.graphicsLayer(
                scaleX = coverState.value.scale,
                scaleY = coverState.value.scale,
                translationX = coverState.value.translationX,
                translationY = coverState.value.translationY
            ),
            displayGridInformation = displayGridInformation,
            cellClickListener = cellClickListener,
            cellSizeDp = cellSizeDp.value
        )
    }

    endPoint()
}

