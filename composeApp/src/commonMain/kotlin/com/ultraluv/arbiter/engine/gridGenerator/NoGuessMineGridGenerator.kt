package com.ultraluv.arbiter.engine.gridGenerator

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.grid.Grid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

class NoGuessMineGridGenerator : GridGenerator {
    override suspend fun generateGrid(
        gridSize: GridSize,
        starCell: Position,
        mineCount: Int
    ): Grid = withContext(Dispatchers.Default) {
        val maxAttempts = 10_000

        repeat(maxAttempts) {
            ensureActive()

            val grid = MineGridGenerator().generateGrid(
                gridSize = gridSize,
                starCell = starCell,
                mineCount = mineCount,
            )

            if (Solver.isSolvable(grid, starCell)) {
                return@withContext grid
            }

            println("Failed to generate grid after $it attempts")
        }

        error("Failed to generate no-guess grid after $maxAttempts attempts")
    }
}