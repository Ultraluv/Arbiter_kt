package com.ultraluv.arbiter.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ultraluv.arbiter.ui.IdleScreen
import com.ultraluv.arbiter.ui.gameScreen_UI.GameScreen
import com.ultraluv.arbiter.ui.homeScreen_UI.HomeScreen
import com.ultraluv.arbiter.picker.PickGuess
import com.ultraluv.arbiter.picker.PickGuessState
import com.ultraluv.arbiter.picker.PickLevel
import com.ultraluv.arbiter.picker.PickLevelState
import com.ultraluv.arbiter.ui.settingScreen_UI.SettingScreen
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
fun Navigation(
    navigationState: NavigationState,
    navigationViewModel: NavigationViewModel,
    endPoint: @Composable () -> Unit,
) {
    var pickLevel: PickLevel by remember{ mutableStateOf(PickLevelState.Hard.toPickLevel()) }
    var pickGuess: PickGuess by remember{ mutableStateOf(PickGuessState.NoGuess.toPickGuess()) }
    AnimatedContent(
        targetState = navigationState,
        transitionSpec = {
            val forward = (this.targetState.order() > this.initialState.order())

            if (forward) {
                fadeIn() + slideInHorizontally { it } togetherWith
                        fadeOut() + slideOutHorizontally { -it }
            } else {
                fadeIn() + slideInHorizontally { -it } togetherWith
                        fadeOut() + slideOutHorizontally { it }
            }

        },
        label = "navigation"
    ){ state ->
        when(state){
            is NavigationState.Idle -> {
                IdleScreen(navigationViewModel)
            }
            is NavigationState.Home -> {
                HomeScreen(navigationViewModel){ level, guess ->
                    pickLevel = level
                    pickGuess = guess
                }
            }
            is NavigationState.Game -> {
                GameScreen(
                    gridSize = pickLevel.gridSize,
                    mineCount = pickLevel.mineCount,
                    isNoGuess = pickGuess.isNoGuess,
                    navigationViewModel = navigationViewModel
                )
            }
            is NavigationState.Setting -> {
                SettingScreen(navigationViewModel)
            }
        }
        endPoint()
    }
}