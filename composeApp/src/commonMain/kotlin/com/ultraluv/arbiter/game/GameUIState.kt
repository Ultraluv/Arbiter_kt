package com.ultraluv.arbiter.game

import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation.DisplayGridInformation

data class GameUIState(
    val displayGridInfo: DisplayGridInformation,
    val mineCount: Int,
    val flagCount: Int,
    val isNoGuess: Boolean,
    val gameState: GameState
)