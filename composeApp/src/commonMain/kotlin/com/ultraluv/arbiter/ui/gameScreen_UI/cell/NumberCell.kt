@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.ultraluv.arbiter.AppIcon
import com.ultraluv.arbiter.MyIconPack
import com.ultraluv.arbiter.canvasIconPath.FlagCanvasIcon
import com.ultraluv.arbiter.canvasIconPath.NumberCanvasIcon
import com.ultraluv.arbiter.loadIcon
import com.ultraluv.arbiter.myiconpack.Circle24dp
import com.ultraluv.arbiter.myiconpack.Counter124dp
import com.ultraluv.arbiter.myiconpack.Counter224dp
import com.ultraluv.arbiter.myiconpack.Counter324dp
import com.ultraluv.arbiter.myiconpack.Counter424dp
import com.ultraluv.arbiter.myiconpack.Counter524dp
import com.ultraluv.arbiter.myiconpack.Counter624dp
import com.ultraluv.arbiter.myiconpack.Counter724dp
import com.ultraluv.arbiter.myiconpack.Counter824dp
import com.ultraluv.arbiter.myiconpack.Flag24dp
import javax.swing.text.StyleConstants.getFontSize

@Suppress("UnusedBoxWithConstraintsScope")
@Composable
fun NumberCell(
    number: Int,
    cellSizeDp: Dp,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(cellSizeDp)
            .padding(cellSizeDp * 0.02f),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.4.dp,
            pressedElevation = 0.2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .onePointClick(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(0.2.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ){
                NumberCanvasIcon(
                    number = number,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}