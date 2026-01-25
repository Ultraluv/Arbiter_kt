package com.ultraluv.arbiter.engine.gridGenerator

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.engine.model.grid.impl.MineGrid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MineGridGenerator:GridGenerator {
    override suspend fun generateGrid(
        gridSize: GridSize,
        starCell: Position,
        mineCount: Int
    ): Grid = withContext(Dispatchers.Default) {
        generateGridProvider(
            gridSize = gridSize,
            starCell = starCell,
            mineCount = mineCount,
        )
    }

    private fun generateGridProvider(
        gridSize: GridSize,
        starCell: Position,
        mineCount: Int,
    ): MineGrid{
        val rows = gridSize.rows
        val cols = gridSize.cols
        val length = rows * cols

        val ignoredIndexes = starCell
            .getIgnoredIndexes(gridSize)
            .toSet()

        val enabledIndexes = (0 until length)
            .filter { it !in ignoredIndexes }

        val mineIndexes = enabledIndexes
            .shuffled()
            .take(mineCount)

        val mineCells = mineIndexes.map {
            MineCell.Mine(
                position = Position(
                    row = it / cols,
                    col = it % cols,
                )
            )
        }

        val zeroCells: MutableCell2DList = MutableList(rows){ row->
            MutableList(cols){ col->
                MineCell.NumberCell.ZeroCell(
                    position = Position(
                        row = row,
                        col = col,
                    )
                )
            }
        }

        val cells = mineCells
            .insertInto(zeroCells)
            .calculateNeighborCell()
            .generateRawCells()

        return MineGrid(
            gridSize = gridSize,
            totalMines = mineCount,
            cells = cells
        )
    }

    private fun Position.getIgnoredIndexes(
        gridSize: GridSize
    ): List<Int>{
        fun toIndex(
            row: Int,
            col: Int,
            gridSize: GridSize
        ): Int{
            return row * gridSize.cols + col
        }

        val currentRow = this.row
        val currentCol = this.col
        val ignoredIndexes = mutableListOf<Int>()

        for(row in currentRow - 1..currentRow + 1){
            for(col in currentCol - 1..currentCol + 1){
                if(row in 0 until gridSize.rows && col in 0 until gridSize.cols){
                    ignoredIndexes.add(toIndex(row, col, gridSize))
                }
            }
        }
        return ignoredIndexes
    }

    private fun List<MineCell>.insertInto(
        zeroCells: MutableCell2DList
    ): MutableCell2DList{
        this.forEach { mineCell ->
            zeroCells[mineCell.position.row][mineCell.position.col] = mineCell
        }
        return zeroCells
    }

    private fun MutableCell2DList.calculateNeighborCell(): MutableCell2DList {
        fun MineCell.calculateNeighborCellProvider(): Int {
            val currentRow = this.position.row
            val currentCol = this.position.col
            var count = 0
            for(row in currentRow - 1..currentRow + 1){
                for(col in currentCol - 1..currentCol + 1){
                    val cell = this@calculateNeighborCell.getOrNull(row)?.getOrNull(col)
                    if(cell != null && cell is MineCell.Mine){
                        count = count.plus(1)
                    }
                }
            }
            return count
        }

        this.forEach { row ->
            row.forEach { cell ->
                if (cell !is MineCell.Mine){
                    this[cell.position.row][cell.position.col] = when(val count = cell.calculateNeighborCellProvider()){
                        0 -> {
                            MineCell.NumberCell.ZeroCell(cell.position)
                        }
                        else -> {
                            MineCell.NumberCell.Cell(cell.position, count)
                        }
                    }
                }
            }
        }

        return this
    }

    private fun MutableCell2DList.generateRawCells(): List<List<RawCell>>{
        return this.map{ row ->
            row.map{ cell ->
                RawCell.UnRevealedCell.UnFlaggedCell(cell)
            }
        }
    }
}