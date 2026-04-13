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
package sokeriaaa.sugarkane.compose.widgets.scaffold.appbar.action

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppDropdownMenuItem(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: DrawableResource? = null,
    contentDescription: String? = text,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        modifier = modifier,
        text = { Text(text) },
        onClick = onClick,
        leadingIcon = iconRes?.let {
            {
                Icon(
                    painter = painterResource(
                        resource = it,
                    ),
                    contentDescription = contentDescription,
                )
            }
        }
    )
}