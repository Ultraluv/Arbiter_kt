package com.ultraluv.arbiter.engine.model.base

data class GridSize(
    val rows: Int,
    val cols: Int,
) {
    companion object {
        fun setGridSize(rows: Int, cols: Int): GridSize =
            GridSize(rows, cols)
    }
}