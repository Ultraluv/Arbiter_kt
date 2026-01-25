package com.ultraluv.arbiter.engine.model.cell

import com.ultraluv.arbiter.engine.model.base.Position

sealed interface MineCell {
    val position: Position

    data class Mine(override val position: Position) : MineCell
    sealed interface NumberCell : MineCell {
        val number: Int

        class ZeroCell(override val position: Position) : NumberCell {
            override val number: Int = 0
        }

        class Cell(override val position: Position, override val number: Int) : NumberCell
    }
}