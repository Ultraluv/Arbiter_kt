package com.ultraluv.arbiter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.canvasIconPath.PlayCanvasIcon
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@Composable
fun IdleScreen(
    navigationViewModel: NavigationViewModel,
) {
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
            Column {
                FloatingActionButton(
                    onClick = {
                        navigationViewModel.goHome()
                    },
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                    ){
                        PlayCanvasIcon(
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
        }
    }
}