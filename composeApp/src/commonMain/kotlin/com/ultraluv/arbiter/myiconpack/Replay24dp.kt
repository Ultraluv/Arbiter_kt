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

public val MyIconPack.Replay24dp: ImageVector
    get() {
        if (_replay24dp != null) {
            return _replay24dp!!
        }
        _replay24dp = Builder(name = "Replay24dp", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 880.0f)
                quadToRelative(-75.0f, 0.0f, -140.5f, -28.5f)
                reflectiveQuadToRelative(-114.0f, -77.0f)
                quadToRelative(-48.5f, -48.5f, -77.0f, -114.0f)
                reflectiveQuadTo(120.0f, 520.0f)
                horizontalLineToRelative(80.0f)
                quadToRelative(0.0f, 117.0f, 81.5f, 198.5f)
                reflectiveQuadTo(480.0f, 800.0f)
                quadToRelative(117.0f, 0.0f, 198.5f, -81.5f)
                reflectiveQuadTo(760.0f, 520.0f)
                quadToRelative(0.0f, -117.0f, -81.5f, -198.5f)
                reflectiveQuadTo(480.0f, 240.0f)
                horizontalLineToRelative(-6.0f)
                lineToRelative(62.0f, 62.0f)
                lineToRelative(-56.0f, 58.0f)
                lineToRelative(-160.0f, -160.0f)
                lineToRelative(160.0f, -160.0f)
                lineToRelative(56.0f, 58.0f)
                lineToRelative(-62.0f, 62.0f)
                horizontalLineToRelative(6.0f)
                quadToRelative(75.0f, 0.0f, 140.5f, 28.5f)
                reflectiveQuadToRelative(114.0f, 77.0f)
                quadToRelative(48.5f, 48.5f, 77.0f, 114.0f)
                reflectiveQuadTo(840.0f, 520.0f)
                quadToRelative(0.0f, 75.0f, -28.5f, 140.5f)
                reflectiveQuadToRelative(-77.0f, 114.0f)
                quadToRelative(-48.5f, 48.5f, -114.0f, 77.0f)
                reflectiveQuadTo(480.0f, 880.0f)
                close()
            }
        }
        .build()
        return _replay24dp!!
    }

private var _replay24dp: ImageVector? = null
