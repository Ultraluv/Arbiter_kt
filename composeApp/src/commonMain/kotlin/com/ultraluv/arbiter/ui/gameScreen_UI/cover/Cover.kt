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
                    detectTransformGestures { _, pan, zoom, _ ->

                        val newScale = (coverState.value.scale * zoom).coerceIn(1f,4f)

                        val scaledWidth = viewportWidthPx * newScale
                        val scaledHeight = viewportHeightPx * newScale

                        val maxX =
                            ((scaledWidth - viewportWidthPx) / 2f).coerceAtLeast(0f)
                        val maxY =
                            ((scaledHeight - viewportHeightPx) / 2f).coerceAtLeast(0f)

                        coverState.value = CoverState(
                            scale = newScale,
                            translationX = (coverState.value.translationX + pan.x).coerceIn(-maxX, maxX),
                            translationY = (coverState.value.translationY + pan.y).coerceIn(-maxY, maxY)
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ){
            onToggle(coverState.value, cellSizeDp)
        }

//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .pointerInput(viewportWidthPx, viewportHeightPx) {
//                    detectTransformGestures { _, pan: Offset, zoom: Float, _, _, changes: List<PointerInputChange> ->
//
//                        val newScale = (coverState.value.scale * zoom).coerceIn(0.8f, 4f)
//
//                        val scaledWidth = viewportWidthPx * newScale
//                        val scaledHeight = viewportHeightPx * newScale
//
//                        val maxX =
//                            ((scaledWidth - viewportWidthPx) / 2f).coerceAtLeast(0f)
//                        val maxY =
//                            ((scaledHeight - viewportHeightPx) / 2f).coerceAtLeast(0f)
//
//
//                        coverState.value = CoverState(
//                            scale = newScale,
//                            translationX = (coverState.value.translationX + pan.x).coerceIn(-maxX, maxX),
//                            translationY = (coverState.value.translationY + pan.y).coerceIn(-maxY, maxY)
//                        )
//
//                        val size = changes.size
//                        if (size > 1) {
//                            changes.forEach { it.consume() }
//                        }
//                    }
//                },
//            contentAlignment = Alignment.Center
//        ){
//            onToggle(coverState.value, cellSizeDp)
//        }
    }
}


//private suspend fun PointerInputScope.detectTransformGestures(
//    panZoomLock: Boolean = false,
//    consume: Boolean = true,
//    pass: PointerEventPass = PointerEventPass.Main,
//    onGestureStart: (PointerInputChange) -> Unit = {},
//    onGestureEnd: (PointerInputChange) -> Unit = {},
//    onGesture: (
//        centroid: Offset,
//        pan: Offset,
//        zoom: Float,
//        rotation: Float,
//        mainPointer: PointerInputChange,
//        changes: List<PointerInputChange>
//    ) -> Unit,
//) {
//
//    awaitEachGesture {
//        var rotation = 0f
//        var zoom = 1f
//        var pan = Offset.Zero
//        var pastTouchSlop = false
//        val touchSlop = viewConfiguration.touchSlop
//        var lockedToPanZoom = false
//
//        // Wait for at least one pointer to press down, and set first contact position
//        val down: PointerInputChange = awaitFirstDown(
//            requireUnconsumed = false,
//            pass = pass
//        )
//        onGestureStart(down)
//
//        var pointer = down
//        // Main pointer is the one that is down initially
//        var pointerId = down.id
//
//        do {
//            val event = awaitPointerEvent(pass = pass)
//
//            // If any position change is consumed from another PointerInputChange
//            // or pointer count requirement is not fulfilled
//            val canceled = event.changes.any { it.isConsumed }
//
//            if (!canceled) {
//
//                // Get pointer that is down, if first pointer is up
//                // get another and use it if other pointers are also down
//                // event.changes.first() doesn't return same order
//                val pointerInputChange = event.changes
//                    .firstOrNull { it.id == pointerId }
//                    ?: event.changes.first()
//
//                // Next time will check same pointer with this id
//                pointerId = pointerInputChange.id
//                pointer = pointerInputChange
//
//                val zoomChange = event.calculateZoom()
//                val rotationChange = event.calculateRotation()
//                val panChange = event.calculatePan()
//
//                if (!pastTouchSlop) {
//                    zoom *= zoomChange
//                    rotation += rotationChange
//                    pan += panChange
//
//                    val centroidSize = event.calculateCentroidSize(useCurrent = false)
//                    val zoomMotion = abs(1 - zoom) * centroidSize
//                    val rotationMotion =
//                        abs(rotation * PI.toFloat() * centroidSize / 180f)
//                    val panMotion = pan.getDistance()
//
//                    if (zoomMotion > touchSlop ||
//                        rotationMotion > touchSlop ||
//                        panMotion > touchSlop
//                    ) {
//                        pastTouchSlop = true
//                        lockedToPanZoom = panZoomLock && rotationMotion < touchSlop
//                    }
//                }
//
//                if (pastTouchSlop) {
//                    val centroid = event.calculateCentroid(useCurrent = false)
//                    val effectiveRotation = if (lockedToPanZoom) 0f else rotationChange
//                    if (effectiveRotation != 0f ||
//                        zoomChange != 1f ||
//                        panChange != Offset.Zero
//                    ) {
//                        onGesture(
//                            centroid,
//                            panChange,
//                            zoomChange,
//                            effectiveRotation,
//                            pointer,
//                            event.changes
//                        )
//                    }
//
//                    if (consume) {
//                        event.changes.forEach { if (it.positionChanged()) it.consume() }
//                    }
//                }
//            }
//        } while (!canceled && event.changes.any { it.pressed })
//
//        onGestureEnd(pointer)
//    }
//}