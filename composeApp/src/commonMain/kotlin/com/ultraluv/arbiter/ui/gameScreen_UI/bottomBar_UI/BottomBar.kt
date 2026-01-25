package com.ultraluv.arbiter.ui.gameScreen_UI.bottomBar_UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.loadIcon

@Composable
fun BottomBar(
    onToClickMode: () -> Unit,
    onToFlagMode: () -> Unit,
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
                modifier = Modifier
                    .wrapContentSize(),
                onClick = {
                    onToClickMode()
                }
            ){
                Column {
                    Icon(painter = loadIcon(AppIcon.Click), contentDescription = "Click")
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .wrapContentSize(),
                onClick = {
                    onToFlagMode()
                }
            ){
                Column {
                    Icon(painter = loadIcon(AppIcon.Flag), contentDescription = "Flag")
                }
            }
        }
    }
}