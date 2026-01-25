package com.ultraluv.arbiter.picker

sealed interface PickGuessState {
    object NoGuess: PickGuessState
    object Normal: PickGuessState

    fun order(): Int{
        return when(this){
            NoGuess -> 0
            Normal -> 1
        }
    }

    fun toPickGuess(): PickGuess{
        return when(this){
            NoGuess -> PickGuess(
                name = "NoGuess",
                isNoGuess = true
            )
            Normal -> PickGuess(
                name = "Normal",
                isNoGuess = false
            )
        }
    }

    companion object {
        fun fromOrder(order: Int): PickGuessState{
            return when(order){
                0 -> NoGuess
                1 -> Normal
                else -> NoGuess
            }
        }
    }
}