package com.ultraluv.arbiter.engine.model.grid.impl

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid

class EmptyMineGrid(
    override val gridSize: GridSize,
    override val totalMines: Int = 0,
    override val cells: List<List<RawCell>>
): Grid {
    override fun get(position: Position): RawCell =
        cells[position.row][position.col]

    override fun getOrNull(position: Position): RawCell? =
        cells.getOrNull(position.row)?.getOrNull(position.col)

    companion object {
        fun create(gridSize: GridSize): EmptyMineGrid {
            val cells = List(gridSize.rows) { row ->
                List(gridSize.cols) { col ->
                    RawCell.UnRevealedCell.UnFlaggedCell(
                        MineCell.NumberCell.ZeroCell(
                            Position(row, col)
                        )
                    )
                }
            }

            return EmptyMineGrid(
                gridSize = gridSize,
                totalMines = 0,
                cells = cells
            )
        }
    }
}