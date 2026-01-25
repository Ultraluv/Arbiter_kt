package com.ultraluv.arbiter

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.ui.gameScreen_UI.GameScreen
import com.ultraluv.arbiter.ui.navigation.Navigation
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
@Preview
actual fun App() {
    val navigationViewModel = remember { NavigationViewModel() }
    val navigationState by navigationViewModel.navigationState

    AndroidBackHandler(navigationViewModel)

    MaterialTheme {
        Navigation(navigationState, navigationViewModel){}
    }
}