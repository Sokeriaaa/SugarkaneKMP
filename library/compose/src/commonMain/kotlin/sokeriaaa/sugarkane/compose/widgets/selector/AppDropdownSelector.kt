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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import org.jetbrains.compose.resources.painterResource
import sokeriaaa.sugarkane.compose.res.Res
import sokeriaaa.sugarkane.compose.res.ic_outline_arrow_drop_down_24

/**
 * Dropdown selector.
 */
@ExperimentalMaterial3Api
@Composable
fun AppDropdownSelector(
    modifier: Modifier = Modifier,
    title: String,
    menuOptions: Array<String>,
    initSelectedOption: Int = 0,
    onItemSelected: (index: Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(
                type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
            ),
            readOnly = true,
            value = menuOptions.getOrNull(initSelectedOption) ?: menuOptions[0],
            onValueChange = { },
            label = { Text(text = title) },
            trailingIcon = { DropdownTrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            menuOptions.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            modifier = Modifier.padding(
                                paddingValues = ExposedDropdownMenuDefaults.ItemContentPadding,
                            ),
                            text = selectionOption,
                        )
                    },
                    onClick = {
                        onItemSelected(index)
                        expanded = false
                    },
                )
            }
        }
    }
}

/**
 * Dropdown selector.
 */
@ExperimentalMaterial3Api
@Composable
fun AppOutlinedDropdownSelector(
    modifier: Modifier = Modifier,
    title: String,
    menuOptions: Array<String>,
    initSelectedOption: Int = 0,
    onItemSelected: (index: Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(
                type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
            ),
            readOnly = true,
            value = menuOptions.getOrNull(initSelectedOption) ?: menuOptions[0],
            onValueChange = { },
            label = { Text(text = title) },
            trailingIcon = { DropdownTrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            menuOptions.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            modifier = Modifier.padding(
                                paddingValues = ExposedDropdownMenuDefaults.ItemContentPadding,
                            ),
                            text = selectionOption,
                        )
                    },
                    onClick = {
                        onItemSelected(index)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun DropdownTrailingIcon(expanded: Boolean) {
    val animatedRotate: Float by animateFloatAsState(
        targetValue = if (expanded) {
            180f
        } else {
            0f
        },
        label = "DropdownMenuTrailer",
    )
    Icon(
        modifier = Modifier.rotate(animatedRotate),
        painter = painterResource(Res.drawable.ic_outline_arrow_drop_down_24),
        contentDescription = null,
    )
}