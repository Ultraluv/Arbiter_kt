package com.ultraluv.arbiter.picker

import com.ultraluv.arbiter.engine.model.base.GridSize


data class PickLevel(
    val level: String,
    val gridSize: GridSize,
    val mineCount: Int,
)