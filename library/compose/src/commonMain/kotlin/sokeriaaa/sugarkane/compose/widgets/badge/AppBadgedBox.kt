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
package sokeriaaa.sugarkane.compose.widgets.badge

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import org.jetbrains.compose.resources.pluralStringResource
import sokeriaaa.sugarkane.compose.res.SKRes
import sokeriaaa.sugarkane.compose.res.notification_count

@Composable
fun AppBadgedBox(
    modifier: Modifier = Modifier,
    badgeNumber: Int,
    content: @Composable BoxScope.() -> Unit,
) {
    val animatedBadgeNumber by animateIntAsState(
        targetValue = badgeNumber.coerceIn(0..100),
        label = "animatedBadgeNumber",
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = if (badgeNumber > 0) 1F else 0F,
        label = "animatedAlpha",
    )
    BadgedBox(
        modifier = modifier,
        badge = {
            Badge(
                modifier = Modifier.alpha(alpha = animatedAlpha)
            ) {
                val badgeContentDescription = pluralStringResource(
                    resource = SKRes.plurals.notification_count,
                    quantity = badgeNumber,
                    /* notifications = */ badgeNumber,
                )
                Text(
                    modifier = Modifier.semantics {
                        contentDescription = badgeContentDescription
                    },
                    text = if (animatedBadgeNumber > 99) {
                        "99+"
                    } else {
                        animatedBadgeNumber.toString()
                    },
                )
            }
        },
        content = content,
    )
}