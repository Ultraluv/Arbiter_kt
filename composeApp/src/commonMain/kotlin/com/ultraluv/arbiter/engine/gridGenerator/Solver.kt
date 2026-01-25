package com.ultraluv.arbiter.engine.gridGenerator

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.engine.model.grid.impl.MutableMineGrid
import com.ultraluv.arbiter.engine.util.mutate

object Solver {
    fun isSolvable(grid: Grid, starCell: Position): Boolean {
        var check = false
        grid.mutate {
            this.revalZeroCell(starCell)
            check = this.solver()
        }
        return check
    }

    // ClearZero
    fun MutableMineGrid.revalZeroCell(
        position: Position
    ) {
        val cell = this.getOrNull(position)

        if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
            val revealed = cell.asRevealed()
            this[position] = revealed
            if (revealed.cell is MineCell.NumberCell.ZeroCell) {
                val currentRow = position.row
                val currentCol = position.col

                for (row in currentRow - 1..currentRow + 1) {
                    for (col in currentCol - 1..currentCol + 1) {
                        val currentPosition = Position(row,col)
                        val cell = this.getOrNull(currentPosition)
                        if (cell != null && cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                            revalZeroCell(currentPosition)
                        }
                    }
                }
            }
        }
    }

    fun MutableMineGrid.solver(): Boolean{
        while (true){
            val oneSetNullSolveCheck = this.oneSet()
            val twoSetNullSolveCheck = this.twoSet()
            val threeSetNullSolveCheck = this.threeSet()
            if(oneSetNullSolveCheck && twoSetNullSolveCheck && threeSetNullSolveCheck){
                break
            }
        }
        return this.isOrNoComplete()
    }

    // isOrNoComplete
    private fun MutableMineGrid.isOrNoComplete(): Boolean {
        val cells = this.cells
        var revealedCellNum = 0
        var flaggedCellNum = 0
        var unFlaggedCellNum = 0

        cells.forEach { row ->
            row.forEach { cell ->
                when(cell){
                    is RawCell.RevealedCell -> revealedCellNum = revealedCellNum.plus(1)
                    is RawCell.UnRevealedCell.FlaggedCell -> flaggedCellNum = flaggedCellNum.plus(1)
                    is RawCell.UnRevealedCell.UnFlaggedCell -> unFlaggedCellNum = unFlaggedCellNum.plus(1)
                }
            }
        }

        return flaggedCellNum == this.totalMines
    }

    // OneSet
    private fun MutableMineGrid.oneSet(): Boolean {
        var check = true
        var changed: Boolean

        do {
            changed = false

            for (row in 0 until gridSize.rows) {
                for (col in 0 until gridSize.cols) {
                    val position = Position(row, col)

                    when(val cell = this[position]){
                        is RawCell.UnRevealedCell.UnFlaggedCell -> continue
                        is RawCell.UnRevealedCell.FlaggedCell -> continue
                        is RawCell.RevealedCell -> {
                            when(val mineCell = cell.cell){
                                is MineCell.Mine -> continue
                                is MineCell.NumberCell.ZeroCell -> {
                                    revalZeroCell(position)
                                }
                                is MineCell.NumberCell.Cell -> {
                                    val neighbors = mineCell.position.neighbors(gridSize)

                                    val unRevealed = neighbors
                                        .map { it to this[it] }
                                        .filter { it.second is RawCell.UnRevealedCell }

                                    val flaggedCount = unRevealed.count {
                                        it.second is RawCell.UnRevealedCell.FlaggedCell
                                    }

                                    val unFlaggedCount = unRevealed.count {
                                        it.second is RawCell.UnRevealedCell.UnFlaggedCell
                                    }

                                    // Flagged
                                    if (flaggedCount + unFlaggedCount == mineCell.number &&
                                        unFlaggedCount > 0
                                    ) {
                                        unRevealed
                                            .filter { it.second is RawCell.UnRevealedCell.UnFlaggedCell }
                                            .forEach { (position, rawCell) ->
                                                this[position] = (rawCell as RawCell.UnRevealedCell.UnFlaggedCell).asFlagged()
                                                changed = true
                                                check = false
                                            }
                                    }

                                    // Revealed
                                    else if (flaggedCount == mineCell.number &&
                                        unFlaggedCount > 0
                                    ) {
                                        unRevealed
                                            .filter { it.second is RawCell.UnRevealedCell.UnFlaggedCell }
                                            .forEach { (position, rawCell) ->
                                                this[position] = (rawCell as RawCell.UnRevealedCell.UnFlaggedCell).asRevealed()
                                                changed = true
                                                check = false
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (changed)

        return check
    }

    // TwoSet
    private fun MutableMineGrid.twoSet(): Boolean {
        var check = true
        var changed: Boolean

        do {
            changed = false

            val numberCells = mutableListOf<Pair<Position, MineCell.NumberCell>>()

            for (row in 0 until gridSize.rows) {
                for (col in 0 until gridSize.cols) {
                    val position = Position(row, col)
                    val cell = this[position]

                    if (cell is RawCell.RevealedCell && cell.cell is MineCell.NumberCell.Cell) {
                        numberCells += position to cell.cell
                    }
                }
            }

            for (i in numberCells.indices) {
                for (j in i + 1 until numberCells.size) {

                    val (posA, cellA) = numberCells[i]
                    val (posB, cellB) = numberCells[j]

                    if (kotlin.math.abs(posA.row - posB.row) > 2 ||
                        kotlin.math.abs(posA.col - posB.col) > 2
                    ) continue

                    val neighborsA = posA.neighbors(gridSize)
                    val neighborsB = posB.neighbors(gridSize)

                    val unFlaggedA = neighborsA
                        .filter { this[it] is RawCell.UnRevealedCell.UnFlaggedCell }
                        .toSet()

                    val unFlaggedB = neighborsB
                        .filter { this[it] is RawCell.UnRevealedCell.UnFlaggedCell }
                        .toSet()

                    val flaggedA = neighborsA.count {
                        this[it] is RawCell.UnRevealedCell.FlaggedCell
                    }
                    val flaggedB = neighborsB.count {
                        this[it] is RawCell.UnRevealedCell.FlaggedCell
                    }

                    val remainA = cellA.number - flaggedA
                    val remainB = cellB.number - flaggedB

                    changed = applyTwoSetRule(
                        unFlaggedA, unFlaggedB,
                        remainA, remainB
                    ) || changed
                    if (changed) check = false
                }
            }
        } while (changed)

        return check
    }

    // ThreeSet
    private fun MutableMineGrid.threeSet(): Boolean {
        // Todo threeSet
        return true
    }

    private fun Position.neighbors(gridSize: GridSize): List<Position> =
        (row - 1..row + 1).flatMap { currentRow ->
            (col - 1..col + 1).mapNotNull { currentCol ->
                when (currentRow) {
                    row if currentCol == col -> {
                        null
                    }
                    in 0 until gridSize.rows if currentCol in 0 until gridSize.cols -> Position(
                        currentRow,
                        currentCol
                    )
                    else -> null
                }
            }
        }

    private fun MutableMineGrid.applyTwoSetRule(
        setA: Set<Position>,
        setB: Set<Position>,
        remainA: Int,
        remainB: Int
    ): Boolean {

        if (setA.containsAll(setB)) {
            return resolveSubset(
                superset = setA,
                subset = setB,
                remainSuper = remainA,
                remainSub = remainB
            )
        }

        if (setB.containsAll(setA)) {
            return resolveSubset(
                superset = setB,
                subset = setA,
                remainSuper = remainB,
                remainSub = remainA
            )
        }

        return false
    }

    private fun MutableMineGrid.resolveSubset(
        superset: Set<Position>,
        subset: Set<Position>,
        remainSuper: Int,
        remainSub: Int
    ): Boolean {
        val different = superset - subset
        if (different.isEmpty()) return false

        if (remainSuper - remainSub == different.size && remainSuper > remainSub) {
            different.forEach { position ->
                val cell = this[position]
                if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                    this[position] = cell.asFlagged()
                }
            }
            return true
        }

        if (remainSuper == remainSub) {
            different.forEach { position ->
                val cell = this[position]
                if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                    this[position] = cell.asRevealed()
                }
            }
            return true
        }

        return false
    }
}
