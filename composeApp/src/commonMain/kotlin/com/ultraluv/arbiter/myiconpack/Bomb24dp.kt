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

public val MyIconPack.Bomb24dp: ImageVector
    get() {
        if (_bomb24dp != null) {
            return _bomb24dp!!
        }
        _bomb24dp = Builder(name = "Bomb24dp", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(346.0f, 912.0f)
                quadToRelative(-125.0f, 0.0f, -212.5f, -88.5f)
                reflectiveQuadTo(46.0f, 610.0f)
                quadToRelative(0.0f, -125.0f, 86.5f, -211.5f)
                reflectiveQuadTo(344.0f, 312.0f)
                horizontalLineToRelative(13.0f)
                lineToRelative(27.0f, -47.0f)
                quadToRelative(12.0f, -22.0f, 36.0f, -28.5f)
                reflectiveQuadToRelative(46.0f, 6.5f)
                lineToRelative(30.0f, 17.0f)
                lineToRelative(5.0f, -8.0f)
                quadToRelative(23.0f, -43.0f, 72.0f, -56.0f)
                reflectiveQuadToRelative(92.0f, 12.0f)
                lineToRelative(35.0f, 20.0f)
                lineToRelative(-40.0f, 69.0f)
                lineToRelative(-35.0f, -20.0f)
                quadToRelative(-14.0f, -8.0f, -30.5f, -3.5f)
                reflectiveQuadTo(570.0f, 292.0f)
                lineToRelative(-5.0f, 8.0f)
                lineToRelative(40.0f, 23.0f)
                quadToRelative(21.0f, 12.0f, 27.5f, 36.0f)
                reflectiveQuadToRelative(-5.5f, 45.0f)
                lineToRelative(-27.0f, 48.0f)
                quadToRelative(23.0f, 36.0f, 34.5f, 76.5f)
                reflectiveQuadTo(646.0f, 612.0f)
                quadToRelative(0.0f, 125.0f, -87.5f, 212.5f)
                reflectiveQuadTo(346.0f, 912.0f)
                close()
                moveTo(346.0f, 832.0f)
                quadToRelative(91.0f, 0.0f, 155.5f, -64.5f)
                reflectiveQuadTo(566.0f, 612.0f)
                quadToRelative(0.0f, -31.0f, -8.5f, -61.0f)
                reflectiveQuadTo(532.0f, 494.0f)
                lineToRelative(-26.0f, -41.0f)
                lineToRelative(42.0f, -72.0f)
                lineToRelative(-104.0f, -60.0f)
                lineToRelative(-42.0f, 72.0f)
                horizontalLineToRelative(-44.0f)
                quadToRelative(-94.0f, 0.0f, -163.5f, 60.0f)
                reflectiveQuadTo(125.0f, 610.0f)
                quadToRelative(0.0f, 92.0f, 64.5f, 157.0f)
                reflectiveQuadTo(346.0f, 832.0f)
                close()
                moveTo(800.0f, 352.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(120.0f)
                verticalLineToRelative(80.0f)
                lineTo(800.0f, 352.0f)
                close()
                moveTo(580.0f, 132.0f)
                verticalLineToRelative(-120.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(120.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(775.0f, 213.0f)
                lineTo(719.0f, 157.0f)
                lineTo(804.0f, 72.0f)
                lineTo(860.0f, 128.0f)
                lineTo(775.0f, 213.0f)
                close()
                moveTo(346.0f, 612.0f)
                close()
            }
        }
        .build()
        return _bomb24dp!!
    }

private var _bomb24dp: ImageVector? = null
