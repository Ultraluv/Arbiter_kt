package com.ultraluv.arbiter.ui.navigation

sealed class NavigationState{
    object Idle : NavigationState()
    object Home : NavigationState()
    object Game : NavigationState()
    object Setting : NavigationState()

    fun order(): Int = when(this) {
        Idle -> 0
        Home -> 1
        Game -> 2
        Setting -> 3
    }
}