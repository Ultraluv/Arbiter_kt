package com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayCell.DisplayCell

data class DisplayGridInformation(
    val gridSize: GridSize,
    val displayCells: MutableList<DisplayCell>,
){
    companion object {
        fun empty(gridSize: GridSize): DisplayGridInformation {
            return DisplayGridInformation(
                gridSize = gridSize,
                displayCells = MutableList(gridSize.rows * gridSize.cols) { DisplayCell.Idle }
            )
        }
    }
}