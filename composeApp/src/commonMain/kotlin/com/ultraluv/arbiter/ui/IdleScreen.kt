package com.ultraluv.arbiter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.loadIcon
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
                    }
                ){
                    Column {
                        Icon(painter = loadIcon(AppIcon.Play), contentDescription = "Play")
                    }
                }
            }
        }
    }
}