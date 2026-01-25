package com.ultraluv.arbiter.ui.gameScreen_UI.action

import com.ultraluv.arbiter.engine.model.base.Position

sealed interface CellClick {
    val position: Position

    data class OnUnFlaggedCellClicked(
        override val position: Position,
    ) : CellClick

    data class OnFlaggedCellClicked(
        override val position: Position,
    ) : CellClick

    data class OnNumberCellClicked(
        override val position: Position,
    ) : CellClick
}