package com.ultraluv.arbiter.ui.gameScreen_UI.action

import com.ultraluv.arbiter.engine.model.base.Position

class CellClickListenerMapper(
    private val position: Position,
    private val listener: CellClickListener,
) : DisplayCellClickListener {

    override fun onUnFlaggedCellClicked() {
        val event = CellClick.OnUnFlaggedCellClicked(position)
        listener.action(event)
    }

    override fun onFlaggedCellClicked() {
        val event = CellClick.OnFlaggedCellClicked(position)
        listener.action(event)
    }

    override fun onNumberCellClicked() {
        val event = CellClick.OnNumberCellClicked(position)
        listener.action(event)
    }
}