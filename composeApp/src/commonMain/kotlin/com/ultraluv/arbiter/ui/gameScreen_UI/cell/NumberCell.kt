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
            .size(cellSizeDp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 1.dp
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
//            Icon(painter = loadIcon(fromNumber(number)), contentDescription = "Number")

//            Icon(
//                imageVector = fromNumber(number),
//                modifier = Modifier
//                    .size(cellSizeDp - 2.dp),
//                contentDescription = "Number"
//            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(0.2.dp * number, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "$number",
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}

//fun fromNumber(number: Int): AppIcon{
//    return when(number){
//        1 -> AppIcon.Counter_1
//        2 -> AppIcon.Counter_2
//        3 -> AppIcon.Counter_3
//        4 -> AppIcon.Counter_4
//        5 -> AppIcon.Counter_5
//        6 -> AppIcon.Counter_6
//        7 -> AppIcon.Counter_7
//        8 -> AppIcon.Counter_8
//        else -> AppIcon.Zero
//    }
//}

fun fromNumber(number: Int): ImageVector {
    return when(number){
        1 -> MyIconPack.Counter124dp
        2 -> MyIconPack.Counter224dp
        3 -> MyIconPack.Counter324dp
        4 -> MyIconPack.Counter424dp
        5 -> MyIconPack.Counter524dp
        6 -> MyIconPack.Counter624dp
        7 -> MyIconPack.Counter724dp
        8 -> MyIconPack.Counter824dp
        else -> MyIconPack.Circle24dp
    }
}