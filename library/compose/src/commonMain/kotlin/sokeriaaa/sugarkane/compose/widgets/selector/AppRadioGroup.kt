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
package sokeriaaa.sugarkane.compose.widgets.selector


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
inline fun <reified T> AppRadioGroup(
    modifier: Modifier = Modifier,
    items: Collection<T>,
    selectedIndex: Int,
    crossinline itemLabel: @Composable (T) -> String = { it.toString() },
    crossinline itemEnabled: (T) -> Boolean = { true },
    crossinline onSelected: (Int) -> Unit,
) {
    Column(
        modifier = modifier.selectableGroup(),
    ) {
        items.forEachIndexed { index, item ->
            val label = itemLabel(item)
            val enabled = itemEnabled(item)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = index == selectedIndex,
                        enabled = enabled,
                        onClick = { onSelected(index) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = index == selectedIndex,
                    enabled = enabled,
                    // null recommended for accessibility with screen readers
                    onClick = null,
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (enabled) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.outline
                    },
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppRadioGroupExample() {
    AppRadioGroup(
        items = listOf("one", "two", "three"),
        selectedIndex = 0,
        onSelected = {},
    )
}