package com.ultraluv.arbiter.engine.model.grid.impl

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid

class MineGrid(
    override val gridSize: GridSize,
    override val totalMines: Int,
    override val cells: List<List<RawCell>>
) : Grid {
    override fun get(position: Position): RawCell {
        return cells[position.row][position.col]
    }

    override fun getOrNull(position: Position): RawCell? {
        return kotlin.runCatching { get(position) }.getOrNull()
    }
}