package com.ultraluv.arbiter.ui.gameScreen_UI.model.displayCell

import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell

data class DisplayCell(
    val position: Position,
    val cell: Cell
) {
    companion object {
        val Idle = DisplayCell(Position.zero(), Cell.UnFlaggedCell)
    }
    sealed interface Cell {
        object UnFlaggedCell : Cell
        object FlaggedCell : Cell
        object Mine : Cell
        object ZeroCell : Cell
        data class NumberCell(val number: Int) : Cell

        companion object {
            fun from(source: RawCell): Cell {
                return when (source) {
                    is RawCell.UnRevealedCell.UnFlaggedCell -> UnFlaggedCell
                    is RawCell.UnRevealedCell.FlaggedCell -> FlaggedCell
                    is RawCell.RevealedCell -> when (val mineCell = source.cell) {
                        is MineCell.Mine -> Mine
                        is MineCell.NumberCell.ZeroCell -> ZeroCell
                        is MineCell.NumberCell.Cell ->
                            NumberCell(mineCell.number)
                    }
                }
            }
        }
    }
}