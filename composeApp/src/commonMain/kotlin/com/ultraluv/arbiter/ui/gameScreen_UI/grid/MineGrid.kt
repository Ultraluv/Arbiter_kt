package com.ultraluv.arbiter.ui.gameScreen_UI.grid

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.ui.gameScreen_UI.action.CellClickListener
import com.ultraluv.arbiter.ui.gameScreen_UI.action.CellClickListenerMapper
import com.ultraluv.arbiter.ui.gameScreen_UI.cell.FlaggedCell
import com.ultraluv.arbiter.ui.gameScreen_UI.cell.Mine
import com.ultraluv.arbiter.ui.gameScreen_UI.cell.NumberCell
import com.ultraluv.arbiter.ui.gameScreen_UI.cell.UnFlaggedCell
import com.ultraluv.arbiter.ui.gameScreen_UI.cell.ZeroCell
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayCell.DisplayCell
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation.DisplayGridInformation


@Composable
fun MineGrid(
    modifier: Modifier,
    displayGridInformation: DisplayGridInformation,
    cellClickListener: CellClickListener,
    cellSizeDp: Dp,
){
    val gridSize = displayGridInformation.gridSize

    Column(modifier = modifier) {
        repeat(gridSize.rows) { row ->
            Row {
                repeat(gridSize.cols) { col ->
                    val position =  Position(row, col)
                    val displayCellClickListener = remember(position) {
                        CellClickListenerMapper(
                            position = position,
                            listener = cellClickListener
                        )
                    }
                    val cell = displayGridInformation.displayCells[row * gridSize.cols + col].cell
                    AnimatedContent(
                        targetState = cell,
                        transitionSpec = {
                            scaleIn(initialScale = 0.8f) togetherWith
                                    scaleOut(targetScale = 0.8f)
                        },
                        label = "MineGrid"
                    ){ displayCell ->
                        when(displayCell){
                            is DisplayCell.Cell.FlaggedCell -> FlaggedCell(
                                cellSizeDp = cellSizeDp,
                                onClick = {
                                    displayCellClickListener.onFlaggedCellClicked()
                                }
                            )
                            is DisplayCell.Cell.UnFlaggedCell -> UnFlaggedCell(
                                cellSizeDp = cellSizeDp,
                                onClick = {
                                    displayCellClickListener.onUnFlaggedCellClicked()
                                }
                            )
                            is DisplayCell.Cell.Mine -> Mine(
                                cellSizeDp = cellSizeDp,
                                onClick = {}
                            )
                            is DisplayCell.Cell.ZeroCell -> ZeroCell(
                                cellSizeDp = cellSizeDp,
                                onClick = {}
                            )
                            is DisplayCell.Cell.NumberCell -> NumberCell(
                                number = displayCell.number,
                                cellSizeDp = cellSizeDp,
                                onClick = {
                                    displayCellClickListener.onNumberCellClicked()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}