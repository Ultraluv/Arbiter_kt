package com.ultraluv.arbiter.engine.gridGenerator

import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.engine.model.grid.impl.MutableMineGrid
import com.ultraluv.arbiter.engine.util.mutate
import kotlin.collections.plusAssign

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
                        val currentPosition = Position(row, col)
                        val cell = this.getOrNull(currentPosition)
                        if (cell != null && cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                            revalZeroCell(currentPosition)
                        }
                    }
                }
            }
        }
    }

    fun MutableMineGrid.solver(): Boolean {
        while (true) {
            val oneSetNullSolveCheck = this.oneSet()
            val twoSetNullSolveCheck = this.twoSet()
            val threeSetNullSolveCheck = this.threeSet()
            if (oneSetNullSolveCheck && twoSetNullSolveCheck && threeSetNullSolveCheck) {
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
                when (cell) {
                    is RawCell.RevealedCell -> revealedCellNum = revealedCellNum.plus(1)
                    is RawCell.UnRevealedCell.FlaggedCell -> flaggedCellNum = flaggedCellNum.plus(1)
                    is RawCell.UnRevealedCell.UnFlaggedCell -> unFlaggedCellNum =
                        unFlaggedCellNum.plus(1)
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

                    when (val cell = this[position]) {
                        is RawCell.UnRevealedCell.UnFlaggedCell -> continue
                        is RawCell.UnRevealedCell.FlaggedCell -> continue
                        is RawCell.RevealedCell -> {
                            when (val mineCell = cell.cell) {
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
                                                this[position] =
                                                    (rawCell as RawCell.UnRevealedCell.UnFlaggedCell).asFlagged()
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
                                                this[position] =
                                                    (rawCell as RawCell.UnRevealedCell.UnFlaggedCell).asRevealed()
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

            val numberCells = getRevealedNumberCells()

            for (i in numberCells.indices) {
                for (j in i + 1 until numberCells.size) {

                    val (posA, cellA) = numberCells[i]
                    val (posB, cellB) = numberCells[j]

                    if (!isNear(posA, posB)) continue

                    val unFlaggedA = posA.neighbors(gridSize).filterUnflagged(this)

                    val unFlaggedB = posB.neighbors(gridSize).filterUnflagged(this)

                    val remainA = cellA.number - posA.neighbors(gridSize).countFlagged(this)
                    val remainB = cellB.number - posB.neighbors(gridSize).countFlagged(this)

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
        var check = true
        var changed: Boolean

        do {
            changed = false
            val numberCells = getRevealedNumberCells()

            for (i in numberCells.indices) {
                for (j in i + 1 until numberCells.size) {
                    for (k in j + 1 until numberCells.size) {
                        val (posA, cellA) = numberCells[i]
                        val (posB, cellB) = numberCells[j]
                        val (posC, cellC) = numberCells[k]

                        if (!isNear(posA, posB) || !isNear(posB, posC) || !isNear(
                                posA,
                                posC
                            )
                        ) continue

                        val unFlaggedA = posA.neighbors(gridSize).filterUnflagged(this)
                        val unFlaggedB = posB.neighbors(gridSize).filterUnflagged(this)
                        val unFlaggedC = posC.neighbors(gridSize).filterUnflagged(this)

                        val remainA = cellA.number - posA.neighbors(gridSize).countFlagged(this)
                        val remainB = cellB.number - posB.neighbors(gridSize).countFlagged(this)
                        val remainC = cellC.number - posC.neighbors(gridSize).countFlagged(this)

                        changed = applyThreeSetRules(
                            unFlaggedA, unFlaggedB, unFlaggedC,
                            remainA, remainB, remainC
                        ) || changed
                        if (changed) check = false
                    }
                }
            }
        } while (changed)

        return check
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

    // to do
    private fun MutableMineGrid.applyThreeSetRules(
        setA: Set<Position>, setB: Set<Position>, setC: Set<Position>,
        rA: Int, rB: Int, rC: Int
    ): Boolean {
        val intersectAB = setA intersect setB
        val intersectBC = setB intersect setC
        val intersectAC = setA intersect setC

        if (intersectAB.isNotEmpty() && intersectBC.isNotEmpty() && intersectAC.isEmpty()) {
            val vSet = setA - intersectAB
            //val wSet = intersectAB
            val xSet = setB - intersectAB - intersectBC
            //val ySet = intersectBC
            val zSet = setC - intersectBC

            // if (b_I >= a_I + c_I && b_I - a_I - c_I == x_I)
            if (rB >= rA + rC && (rB - rA - rC) == xSet.size) {
                var anyChanged = false
                xSet.forEach { if (markAsFlagged(it)) anyChanged = true }
                vSet.forEach { if (markAsRevealed(it)) anyChanged = true }
                zSet.forEach { if (markAsRevealed(it)) anyChanged = true }
                return anyChanged
            }

            // if (a_I + c_I >= b_I && a_I + c_I - b_I == v_I + z_I)
            if (rA + rC >= rB && (rA + rC - rB) == (vSet.size + zSet.size)) {
                var anyChanged = false
                vSet.forEach { if (markAsFlagged(it)) anyChanged = true }
                zSet.forEach { if (markAsFlagged(it)) anyChanged = true }
                xSet.forEach { if (markAsRevealed(it)) anyChanged = true }
                return anyChanged
            }
        } else if (intersectAB.isNotEmpty() && intersectBC.isNotEmpty()) {
            val coreABC = intersectAB intersect setC // 三者公共交集区域 (x_I 在 C 代码 B 逻辑中)

            if (coreABC.isNotEmpty() && coreABC.size < setA.size) {

                val onlyAB = intersectAB - coreABC // 仅 A, B 相交
                val onlyBC = intersectBC - coreABC // 仅 B, C 相交
                //val onlyAC = intersectAC - coreABC // 仅 A, C 相交

                val onlyA = setA - intersectAB - intersectAC // 仅 A 独有
                val onlyB = setB - intersectAB - intersectBC // 仅 B 独有
                val onlyC = setC - intersectAC - intersectBC // 仅 C 独有

                // if (b_I == a_I + c_I)
                if (rB == rA + rC && rB > 0) {
                    var anyChanged = false

                    onlyA.forEach { if (markAsFlagged(it)) anyChanged = true }
                    onlyC.forEach { if (markAsFlagged(it)) anyChanged = true }

                    onlyAB.forEach { if (markAsFlagged(it)) anyChanged = true }
                    onlyBC.forEach { if (markAsFlagged(it)) anyChanged = true }
                    coreABC.forEach { if (markAsFlagged(it)) anyChanged = true }

                    if (anyChanged) return true
                }

                // a_I + c_I - b_I == 外部区域总和
                val outerSum = onlyA.size + coreABC.size + onlyC.size
                if (rA + rC - rB == outerSum && outerSum > 0) {
                    var anyChanged = false
                    onlyA.forEach { if (markAsFlagged(it)) anyChanged = true }
                    coreABC.forEach { if (markAsFlagged(it)) anyChanged = true }
                    onlyC.forEach { if (markAsFlagged(it)) anyChanged = true }

                    onlyB.forEach { if (markAsRevealed(it)) anyChanged = true }
                    return anyChanged
                }
            }
        }
        return false
    }

    private fun List<Position>.filterUnflagged(grid: Grid) =
        this.filter { grid[it] is RawCell.UnRevealedCell.UnFlaggedCell }.toSet()

    private fun List<Position>.countFlagged(grid: Grid) =
        this.count { grid[it] is RawCell.UnRevealedCell.FlaggedCell }

    private fun MutableMineGrid.markAsFlagged(pos: Position): Boolean {
        val cell = this[pos]
        if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
            this[pos] = cell.asFlagged()
            return true
        }
        return false
    }

    private fun MutableMineGrid.markAsRevealed(pos: Position): Boolean {
        val cell = this[pos]
        if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
            this[pos] = cell.asRevealed()
            return true
        }
        return false
    }

    private fun isNear(p1: Position, p2: Position) =
        kotlin.math.abs(p1.row - p2.row) <= 2 && kotlin.math.abs(p1.col - p2.col) <= 2

    private fun MutableMineGrid.getRevealedNumberCells(): MutableList<Pair<Position, MineCell.NumberCell>> {
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
        return numberCells
    }
}
