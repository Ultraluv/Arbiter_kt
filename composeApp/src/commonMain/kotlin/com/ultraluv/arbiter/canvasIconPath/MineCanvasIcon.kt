package com.ultraluv.arbiter.canvasIconPath

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser

@Composable
fun MineCanvasIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1f1f1f)
) {
    val path = remember {
        PathParser().parsePathString("m336-280 144-144 144 144 56-56-144-144 144-144-56-56-144 144-144-144-56 56 144 144-144 144 56 56ZM480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z").toPath()
    }

    Canvas(modifier = modifier) {
        val viewport = 960f

        val scale = minOf(size.width / viewport, size.height / viewport)

        val offsetX = (size.width - viewport * scale) / 2f
        val offsetY = (size.height - viewport * scale) / 2f

        withTransform({
            translate(offsetX, offsetY)
            scale(scale, scale, pivot = Offset.Zero)
            translate(0f, viewport)
        }) {
            drawPath(
                path = path,
                color = color
            )
        }
    }
}