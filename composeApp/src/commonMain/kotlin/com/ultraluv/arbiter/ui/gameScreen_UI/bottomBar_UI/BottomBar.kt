package com.ultraluv.arbiter.ui.gameScreen_UI.bottomBar_UI

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.canvasIconPath.AnimateBaseCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.BaseCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.IconPathData
import com.ultraluv.arbiter.mode.ModeState
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
fun BottomBar(
    navigationViewModel: NavigationViewModel,
    modeState: ModeState,
    onChangeMode: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    navigationViewModel.onBack()
                }
            ){
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    AnimateBaseCanvasIcon(
                        modifier = Modifier
                            .size(24.dp),
                        iconPathData = IconPathData.Back
                    )
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .wrapContentSize(),
                onClick = {
                    onChangeMode()
                }
            ){
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    AnimatedContent(
                        targetState = modeState,
                        label = "modeState"
                    ){ state ->
                        when(state){
                            ModeState.Click -> {
                                BaseCanvasIcon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    iconPathData = IconPathData.Click
                                )
                            }
                            ModeState.Flag -> {
                                BaseCanvasIcon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    iconPathData = IconPathData.Flag
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}