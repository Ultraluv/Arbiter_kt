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

public val MyIconPack.PlayArrow24dp: ImageVector
    get() {
        if (_playArrow24dp != null) {
            return _playArrow24dp!!
        }
        _playArrow24dp = Builder(name = "PlayArrow24dp", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(320.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                lineToRelative(440.0f, 280.0f)
                lineToRelative(-440.0f, 280.0f)
                close()
                moveTo(400.0f, 480.0f)
                close()
                moveTo(400.0f, 614.0f)
                lineTo(610.0f, 480.0f)
                lineTo(400.0f, 346.0f)
                verticalLineToRelative(268.0f)
                close()
            }
        }
        .build()
        return _playArrow24dp!!
    }

private var _playArrow24dp: ImageVector? = null
