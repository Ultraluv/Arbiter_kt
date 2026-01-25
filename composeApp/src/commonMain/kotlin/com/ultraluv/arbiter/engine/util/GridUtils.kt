package com.ultraluv.arbiter.engine.util

import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.engine.model.grid.impl.MutableMineGrid

internal fun Grid.mutate(
    mutation: MutableMineGrid.() -> Unit,
): Grid = MutableMineGrid
    .from(this)
    .apply(mutation)