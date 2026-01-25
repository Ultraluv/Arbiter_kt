package com.ultraluv.arbiter.engine.gridGenerator

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.grid.Grid

interface GridGenerator {
    suspend fun generateGrid(
        gridSize: GridSize,
        starCell: Position,
        mineCount: Int,
    ): Grid
}