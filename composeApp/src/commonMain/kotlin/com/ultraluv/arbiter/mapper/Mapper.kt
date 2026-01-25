package com.ultraluv.arbiter.mapper

import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayCell.DisplayCell
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation.DisplayGridInformation

fun Grid.toDisplayGridInformation(): DisplayGridInformation {
    return DisplayGridInformation(
        gridSize = this.gridSize,
        displayCells = this.cells.flatten().map { rawCell ->
            DisplayCell(
                position = rawCell.position,
                cell = DisplayCell.Cell.from(rawCell)
            )
        }.toMutableList()
    )
}
