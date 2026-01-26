package com.ultraluv.arbiter.ui.gameScreen_UI.topBar_UI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.game.GameState
import com.ultraluv.arbiter.loadIcon

@Composable
fun TopBar(
    mineCount: Int,
    flagCount: Int,
    gameState: GameState,
    onRePlay: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ){
        FloatingActionButton(
            modifier = Modifier
                .wrapContentSize(),
            onClick = {
                if(gameState == GameState.Victory || gameState == GameState.Defeat){
                    onRePlay()
                }
            }
        ){
            val modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            when (gameState) {
                GameState.Idle -> {
                    Text("Tap a Cell To Start", modifier)
                }

                GameState.Loading -> {
                    Text("Game Loading", modifier)
                }

                GameState.Playing -> {
                    Row {
                        Text("Mine Count: $mineCount", modifier)
                        Text("Flag Count: $flagCount", modifier)
                    }
                }
                GameState.Victory -> {
                    Icon(painter = loadIcon(AppIcon.RePlay), contentDescription = "RePlay")
                }
                GameState.Defeat -> {
                    Icon(painter = loadIcon(AppIcon.RePlay), contentDescription = "RePlay")
                }
            }
        }
    }
}