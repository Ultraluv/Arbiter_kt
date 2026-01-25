package com.ultraluv.arbiter.mode

sealed interface ModeState{
    object Flag: ModeState
    object Click: ModeState
}