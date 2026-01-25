package com.ultraluv.arbiter.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ultraluv.arbiter.ui.BackDispatcher
import com.ultraluv.arbiter.ui.navigation.NavigationState

class NavigationViewModel: BackDispatcher {

    private val backStack = mutableListOf<NavigationState>()
    private val _navigationState = mutableStateOf<NavigationState>(
        NavigationState.Idle
    )
    val navigationState: State<NavigationState> = _navigationState

    fun goHome() = navigateTo(NavigationState.Home)

    fun startGame() = navigateTo(NavigationState.Game)

    fun openSetting() = navigateTo(NavigationState.Setting)

    private fun navigateTo(state: NavigationState) {
        backStack.add(_navigationState.value)
        _navigationState.value = state
    }

    fun popBackStack(): Boolean {
        if (backStack.isEmpty()) return false

        val last = backStack.removeLastOrNull()
        _navigationState.value = last ?: return false
        return true
    }

    fun canPop(): Boolean = backStack.isNotEmpty()

    override fun onBack(): Boolean {
        return popBackStack()
    }
}