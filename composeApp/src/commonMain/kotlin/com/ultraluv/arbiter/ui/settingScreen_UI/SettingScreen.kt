package com.ultraluv.arbiter.ui.settingScreen_UI

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
fun SettingScreen(
    navigationViewModel: NavigationViewModel,
){
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
            FloatingActionButton(
                onClick = {
                    navigationViewModel.onBack()
                }
            ){
                Column {
                    Icon(painter = loadIcon(AppIcon.Back), contentDescription = "Back")
                }
            }
        }
    }
}