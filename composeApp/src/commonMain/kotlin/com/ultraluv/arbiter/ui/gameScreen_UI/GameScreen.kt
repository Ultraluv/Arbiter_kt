package com.ultraluv.arbiter.ui.gameScreen_UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.game.GameState
import com.ultraluv.arbiter.ui.gameScreen_UI.board.Board
import com.ultraluv.arbiter.ui.gameScreen_UI.bottomBar_UI.BottomBar
import com.ultraluv.arbiter.ui.gameScreen_UI.topBar_UI.TopBar
import com.ultraluv.arbiter.viewmodel.GameViewModel
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
fun GameScreen(
    gridSize: GridSize,
    mineCount: Int,
    isNoGuess: Boolean = true,
    navigationViewModel: NavigationViewModel,
){
    val gameViewModel = remember { GameViewModel() }
    val gameUIState by gameViewModel.gameUIState
    val modeState by gameViewModel.modeState

    Scaffold(
        topBar = {
            TopBar(
                mineCount = gameUIState.mineCount,
                flagCount = gameUIState.flagCount,
                gameState = gameUIState.gameState,
                onRePlay = {
                    gameViewModel.rePlay(
                        gridSize = gridSize,
                        mineCount = mineCount,
                        isNoGuess = isNoGuess
                    )
                }
            )
        },
        bottomBar = {
            BottomBar(
                navigationViewModel = navigationViewModel,
                modeState = modeState,
                onChangeMode = {
                    gameViewModel.onChangeMode()
                }
            )
        }
    ){ padding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Board(
                displayGridInformation = gameUIState.displayGridInfo,
                cellClickListener = gameViewModel.cellClickListener
            ){
                when (gameUIState.gameState) {
                    GameState.Idle -> {
                        LaunchedEffect(Unit) {
                            gameViewModel.idleGame(
                                gridSize = gridSize,
                                mineCount = mineCount,
                                isNoGuess = isNoGuess
                            )
                        }

                        Text("${gameUIState.gameState}")
                    }

                    GameState.Loading -> {
                        Text("${gameUIState.gameState}")
                    }

                    GameState.Playing -> {
                        Text("${gameUIState.gameState}")
                    }

                    GameState.Victory -> {

                    }
                    GameState.Defeat -> {

                    }
                }
            }
        }
    }
}
