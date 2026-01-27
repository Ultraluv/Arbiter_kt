package com.ultraluv.arbiter.ui.homeScreen_UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swmansion.kmpwheelpicker.WheelPicker
import com.swmansion.kmpwheelpicker.rememberWheelPickerState
import com.ultraluv.arbiter.canvasIconPath.BackCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.SettingCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.StartCanvasIcon
import com.ultraluv.arbiter.picker.PickGuess
import com.ultraluv.arbiter.picker.PickGuessState
import com.ultraluv.arbiter.picker.PickLevel
import com.ultraluv.arbiter.picker.PickLevelState
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
fun HomeScreen(
    navigationViewModel: NavigationViewModel,
    onStartGame: (PickLevel, PickGuess) -> Unit,
){
    val pickerLevelState = rememberWheelPickerState(itemCount = 4, initialIndex = 2)
    val pickerGuessState = rememberWheelPickerState(itemCount = 2, initialIndex = 0)
    var pickLevel: PickLevel by remember{ mutableStateOf(PickLevelState.Hard.toPickLevel()) }
    pickLevel = PickLevelState.fromOrder(pickerLevelState.value.toInt()).toPickLevel()
    var pickGuess: PickGuess by remember{ mutableStateOf(PickGuessState.NoGuess.toPickGuess()) }
    pickGuess = PickGuessState.fromOrder(pickerGuessState.value.toInt()).toPickGuess()

    Scaffold(
        topBar = {
            Box(modifier = Modifier.statusBarsPadding())
        }
    ){ padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center,
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // WheelPicker pickerGuessState
                WheelPicker(
                    state = pickerGuessState,
                    bufferSize = 2,
                    window = {
                        Box(
                            Modifier.background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                MaterialTheme.shapes.small,
                            )
                        )
                    },
                ) { index ->
                    val pickGuess = PickGuessState.fromOrder(index).toPickGuess()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(pickGuess.name, Modifier.padding(20.dp))
                        }
                    }
                }

                // WheelPicker pickerLevelState
                WheelPicker(
                    state = pickerLevelState,
                    bufferSize = 3,
                    window = {
                        Box(
                            Modifier.background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                MaterialTheme.shapes.small,
                            )
                        )
                    },
                ) { index ->
                    val pickLevel = PickLevelState.fromOrder(index).toPickLevel()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(pickLevel.level)
                            Text("${pickLevel.gridSize}")
                            Text("${pickLevel.mineCount}")
                        }
                    }
                }

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
                            BackCanvasIcon(
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            onStartGame(pickLevel, pickGuess)
                            navigationViewModel.startGame()
                        }
                    ){
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                        ){
                            StartCanvasIcon(
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            navigationViewModel.openSetting()
                        }
                    ){
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                        ){
                            SettingCanvasIcon(
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}