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
fun RePlayCanvasIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1f1f1f)
) {
    val path = remember {
        PathParser().parsePathString("M480-80q-75 0-140.5-28.5t-114-77q-48.5-48.5-77-114T120-440h80q0 117 81.5 198.5T480-160q117 0 198.5-81.5T760-440q0-117-81.5-198.5T480-720h-6l62 62-56 58-160-160 160-160 56 58-62 62h6q75 0 140.5 28.5t114 77q48.5 48.5 77 114T840-440q0 75-28.5 140.5t-77 114q-48.5 48.5-114 77T480-80Z").toPath()
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