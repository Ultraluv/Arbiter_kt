package com.ultraluv.arbiter.engine.model.cell

import com.ultraluv.arbiter.engine.model.base.Position

sealed interface RawCell {
    val position: Position

    sealed interface UnRevealedCell: RawCell {
        fun asRevealed(): RevealedCell
        data class UnFlaggedCell(
            val cell: MineCell
        ): UnRevealedCell {
            override val position: Position = cell.position
            override fun asRevealed(): RevealedCell = RevealedCell(cell)
            fun asFlagged(): FlaggedCell = FlaggedCell(cell)
        }
        data class FlaggedCell(
            val cell: MineCell
        ): UnRevealedCell {
            override val position: Position = cell.position
            override fun asRevealed(): RevealedCell = RevealedCell(cell)
            fun asUnFlagged(): UnFlaggedCell = UnFlaggedCell(cell)
        }
    }

    data class RevealedCell(
        val cell: MineCell
    ): RawCell {
        override val position: Position = cell.position
    }
}