package com.ultraluv.arbiter

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.ui.gameScreen_UI.GameScreen
import com.ultraluv.arbiter.ui.navigation.Navigation
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
actual fun App() {
    MaterialTheme {
        val navigationViewModel = remember { NavigationViewModel() }
        val navigationState by navigationViewModel.navigationState
        Box(
            modifier = Modifier.onPreviewKeyEvent {
                if (it.key == Key.Escape && it.type == KeyEventType.KeyDown) {
                    navigationViewModel.onBack()
                    true
                } else {
                    false
                }
            }
        ){
            Navigation(navigationState, navigationViewModel){}
        }
    }
}