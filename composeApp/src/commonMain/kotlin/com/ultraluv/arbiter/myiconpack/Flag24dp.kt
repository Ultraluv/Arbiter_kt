package com.ultraluv.arbiter.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.ultraluv.arbiter.MyIconPack

public val MyIconPack.Flag24dp: ImageVector
    get() {
        if (_flag24dp != null) {
            return _flag24dp!!
        }
        _flag24dp = Builder(name = "Flag24dp", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(200.0f, 840.0f)
                verticalLineToRelative(-680.0f)
                horizontalLineToRelative(360.0f)
                lineToRelative(16.0f, 80.0f)
                horizontalLineToRelative(224.0f)
                verticalLineToRelative(400.0f)
                lineTo(520.0f, 640.0f)
                lineToRelative(-16.0f, -80.0f)
                lineTo(280.0f, 560.0f)
                verticalLineToRelative(280.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(500.0f, 400.0f)
                close()
                moveTo(586.0f, 560.0f)
                horizontalLineToRelative(134.0f)
                verticalLineToRelative(-240.0f)
                lineTo(510.0f, 320.0f)
                lineToRelative(-16.0f, -80.0f)
                lineTo(280.0f, 240.0f)
                verticalLineToRelative(240.0f)
                horizontalLineToRelative(290.0f)
                lineToRelative(16.0f, 80.0f)
                close()
            }
        }
        .build()
        return _flag24dp!!
    }

private var _flag24dp: ImageVector? = null
