package com.ultraluv.arbiter.engine.model.base

data class Position(
    val row: Int,
    val col: Int,
) {
    companion object {
        fun zero(): Position = Position(row = 0, col = 0)
    }
}