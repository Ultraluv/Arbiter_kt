package com.ultraluv.arbiter.ui.gameScreen_UI.cell

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
fun Modifier.onePointClick(
    isEnableHaptic: Boolean = true,
    onClick: () -> Unit
): Modifier{
    val haptic = LocalHapticFeedback.current
    return this.pointerInput(Unit) {
        awaitEachGesture {
            var isOnePointer = true
            var pointerCount = 1
            var noMoved = true
            do {
                val event = awaitPointerEvent()

                pointerCount = event.changes.count { it.pressed }

                if (pointerCount > 1) {
                    isOnePointer = false
                }

                if (event.changes.any { it.positionChanged() }) {
                    noMoved = false
                    isOnePointer = false
                }

            } while (event.changes.any { it.pressed })

            if (isOnePointer && noMoved) {
                if (isEnableHaptic){
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                onClick()
            }
        }
    }
}