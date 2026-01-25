package com.ultraluv.arbiter.engine.model.grid

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.RawCell

interface Grid {

    val gridSize: GridSize

    val totalMines: Int

    val cells: List<List<RawCell>>

    operator fun get(position: Position): RawCell

    fun getOrNull(position: Position): RawCell?
}