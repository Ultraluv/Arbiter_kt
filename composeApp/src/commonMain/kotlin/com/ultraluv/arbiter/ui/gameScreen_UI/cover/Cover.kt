@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.ultraluv.arbiter.ui.gameScreen_UI.cover

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.ultraluv.arbiter.engine.model.base.GridSize
import kotlin.math.min

@Composable
fun Cover(
    gridSize: GridSize,
    onToggle: @Composable (CoverState, Dp) -> Unit,
){
    val coverState = remember {
        mutableStateOf(
            CoverState(
                scale = 1f,
                translationX = 0f,
                translationY = 0f
            )
        )
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current

        // pixel size / 1344 * 2992 / 1192 * 2728
        val viewportWidthPx = constraints.maxWidth
        val viewportHeightPx = constraints.maxHeight

        // cellSizeDp
        val cellSizeDp = with(density) {
            min(
                viewportWidthPx / gridSize.cols,
                viewportHeightPx / gridSize.rows
            ).toDp()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(viewportWidthPx, viewportHeightPx) {
                    detectTransformGestures { centroid, pan, zoom, _ ->

                        val oldScale = coverState.value.scale
                        val newScale = (oldScale * zoom).coerceIn(1f, 6f)

                        val scaleFactor = newScale / oldScale

                        val addX = pan.x - (centroid.x - viewportWidthPx / 2f - coverState.value.translationX) * (scaleFactor - 1)
                        val addY = pan.y - (centroid.y - viewportHeightPx / 2f - coverState.value.translationY) * (scaleFactor - 1)

                        val nextX = coverState.value.translationX + addX
                        val nextY = coverState.value.translationY + addY

                        val maxX = ((viewportWidthPx * newScale - viewportWidthPx) / 2f).coerceAtLeast(0f)
                        val maxY = ((viewportHeightPx * newScale - viewportHeightPx) / 2f).coerceAtLeast(0f)

                        coverState.value = CoverState(
                            scale = newScale,
                            translationX = nextX.coerceIn(-maxX, maxX),
                            translationY = nextY.coerceIn(-maxY, maxY)
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ){
            onToggle(coverState.value, cellSizeDp)
        }
    }
}