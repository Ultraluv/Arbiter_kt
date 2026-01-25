package com.ultraluv.arbiter.engine.model.grid.impl

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid

class MutableMineGrid(
    override val gridSize: GridSize,
    override val totalMines: Int,
    private val mutableCells: MutableList<MutableList<RawCell>> = mutableListOf()
) : Grid {
    override val cells = mutableCells

    override fun get(position: Position): RawCell {
        return cells[position.row][position.col]
    }

    override fun getOrNull(position: Position): RawCell? {
        return kotlin.runCatching { get(position) }.getOrNull()
    }

    operator fun set(position: Position, value: RawCell) {
        mutableCells[position.row][position.col] = value
    }

    companion object{
        fun from(grid: Grid): MutableMineGrid {
            return MutableMineGrid(
                gridSize = grid.gridSize,
                totalMines = grid.totalMines,
                mutableCells = grid.cells.map { it.toMutableList() }.toMutableList()
            )
        }
    }
}