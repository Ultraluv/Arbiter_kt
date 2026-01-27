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
fun StartCanvasIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1f1f1f)
) {
    val path = remember {
        PathParser().parsePathString("M320-200v-560l440 280-440 280Zm80-280Zm0 134 210-134-210-134v268Z").toPath()
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