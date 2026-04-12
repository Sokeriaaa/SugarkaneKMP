/**
 * Copyright (C) 2026 Sokeriaaa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sokeriaaa.sugarkane.compose.ui.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Block clicks behind it.
 *
 * @param dimAlpha The dim alpha of the background.
 */
@Composable
fun ModalOverlay(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dimAlpha: Float = if (enabled) 0.2F else 0F,
    content: @Composable BoxScope.() -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val animatedAlpha by animateFloatAsState(
        targetValue = dimAlpha,
        label = "animatedAlpha",
    )
    // Force focus when shown
    LaunchedEffect(enabled) {
        if (enabled) {
            focusRequester.requestFocus()
        } else {
            focusRequester.freeFocus()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            // Blocks ALL clicks behind it
            .modalInputBlocker(enabled = enabled)
            // Focus requester
            .focusRequester(focusRequester)
            .focusable()
            // Visual dim
            .background(MaterialTheme.colorScheme.scrim.copy(alpha = animatedAlpha)),
        content = content
    )
}

/**
 * Blocks ALL clicks behind it
 */
fun Modifier.modalInputBlocker(enabled: Boolean): Modifier =
    if (enabled) {
        this.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    awaitPointerEvent()
                }
            }
        }.onPreviewKeyEvent { true }
    } else {
        this
    }