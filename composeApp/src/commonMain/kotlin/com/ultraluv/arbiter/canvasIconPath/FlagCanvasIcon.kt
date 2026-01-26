package com.ultraluv.arbiter.canvasIconPath

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate


@Composable
fun FlagCanvasIcon(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF1f1f1f)
) {
    Canvas(modifier = modifier) {

        val viewport = 960f

        val scale = minOf(
            size.width / viewport,
            size.height / viewport
        )

        val dx = (size.width - viewport * scale) / 2f
        val dy = (size.height - viewport * scale) / 2f

        translate(dx, dy) {
            scale(scale, scale) {

                val path = Path().apply {
                    moveTo(200f, 840f)
                    relativeLineTo(0f, -680f)
                    relativeLineTo(360f, 0f)
                    relativeLineTo(16f, 80f)
                    relativeLineTo(224f, 0f)
                    relativeLineTo(0f, 400f)
                    lineTo(520f, 640f)
                    relativeLineTo(-16f, -80f)
                    lineTo(280f, 560f)
                    relativeLineTo(0f, 280f)
                    relativeLineTo(-80f, 0f)
                    close()

                    moveTo(500f, 400f)
                    close()

                    moveTo(586f, 560f)
                    relativeLineTo(134f, 0f)
                    relativeLineTo(0f, -240f)
                    lineTo(510f, 320f)
                    relativeLineTo(-16f, -80f)
                    lineTo(280f, 240f)
                    relativeLineTo(0f, 240f)
                    relativeLineTo(290f, 0f)
                    relativeLineTo(16f, 80f)
                    close()
                }

                drawPath(
                    path = path,
                    color = color
                )
            }
        }
    }
}
