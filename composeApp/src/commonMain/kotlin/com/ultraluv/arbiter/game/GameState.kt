package com.ultraluv.arbiter.game

sealed class GameState{
    object Idle : GameState()
    object Loading : GameState()
    object Playing : GameState()
    object Victory : GameState()
    object Defeat : GameState()
}