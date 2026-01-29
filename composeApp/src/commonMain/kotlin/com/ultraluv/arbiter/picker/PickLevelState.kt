package com.ultraluv.arbiter.picker

import com.ultraluv.arbiter.engine.model.base.GridSize

sealed interface PickLevelState {
    object Easy: PickLevelState
    object Medium: PickLevelState
    object Hard: PickLevelState
    object Extreme: PickLevelState

    fun order(): Int{
        return when(this){
            Easy -> 0
            Medium -> 1
            Hard -> 2
            Extreme -> 3
        }
    }

    fun toPickLevel(): PickLevel{
        return when(this){
            Easy -> PickLevel(
                level = "Easy",
                gridSize = GridSize(10, 10),
                mineCount = 10
            )
            Medium -> PickLevel(
                level = "Medium",
                gridSize = GridSize(16, 16),
                mineCount = 35
            )
            Hard -> PickLevel(
                level = "Hard",
                gridSize = GridSize(30, 16),
                mineCount = 50 // 99
            )
            Extreme -> PickLevel(
                level = "Extreme",
                gridSize = GridSize(30, 30),
                mineCount = 90 // 200
            )
        }
    }

    companion object {
        fun fromOrder(order: Int): PickLevelState{
            return when(order){
                0 -> Easy
                1 -> Medium
                2 -> Hard
                3 -> Extreme
                else -> Hard
            }
        }
    }
}