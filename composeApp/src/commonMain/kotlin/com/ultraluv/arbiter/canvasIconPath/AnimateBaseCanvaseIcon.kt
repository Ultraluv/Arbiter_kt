package com.ultraluv.arbiter.canvasIconPath

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser

@Composable
fun AnimateBaseCanvasIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1f1f1f),
    iconPathData: IconPathData,
) {
    val fullPath = remember {
        PathParser().parsePathString(iconPathData.pathData).toPath()
    }

    val pathMeasure = remember { PathMeasure() }
    pathMeasure.setPath(fullPath, false)
    val totalLength = pathMeasure.length

    val animateProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animateProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    Canvas(modifier = modifier) {
        val viewport = 960f
        val scale = minOf(size.width / viewport, size.height / viewport)
        val offsetX = (size.width - viewport * scale) / 2f
        val offsetY = (size.height - viewport * scale) / 2f

        val destinationPath = Path()
        pathMeasure.getSegment(
            startDistance = 0f,
            stopDistance = totalLength * animateProgress.value,
            destination = destinationPath
        )

        withTransform({
            translate(offsetX, offsetY)
            scale(scale, scale, pivot = Offset.Zero)
            translate(0f, viewport)
        }) {
            drawPath(
                path = destinationPath,
                color = color
            )
        }
    }
}