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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import sokeriaaa.sugarkane.compose.res.SKRes
import sokeriaaa.sugarkane.compose.res.decrease
import sokeriaaa.sugarkane.compose.res.ic_outline_add_circle_24
import sokeriaaa.sugarkane.compose.res.ic_outline_do_not_disturb_on_24
import sokeriaaa.sugarkane.compose.res.increase
import sokeriaaa.sugarkane.compose.widgets.button.AppIconButton

/**
 * A simple amount selector.
 */
@Composable
fun AppAmountSelector(
    amount: Int,
    modifier: Modifier = Modifier,
    minimum: Int = 1,
    maximum: Int = Int.MAX_VALUE,
    color: Color = MaterialTheme.colorScheme.onSurface,
    focusManager: FocusManager = LocalFocusManager.current,
    onAmountChange: (Int) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppAmountSelectorContent(
            amount = amount,
            minimum = minimum,
            maximum = maximum,
            color = color,
            focusManager = focusManager,
            onAmountChange = onAmountChange,
        )
    }
}

/**
 * The content of the amount selector. Can be used in a row or a column.
 */
@Composable
fun AppAmountSelectorContent(
    amount: Int,
    minimum: Int = 1,
    maximum: Int = Int.MAX_VALUE,
    color: Color = MaterialTheme.colorScheme.onSurface,
    focusManager: FocusManager = LocalFocusManager.current,
    onAmountChange: (Int) -> Unit,
) {
    var amountText: String by remember { mutableStateOf("") }
    LaunchedEffect(amount) {
        amountText = amount.toString()
    }
    // -
    AppIconButton(
        iconRes = SKRes.drawable.ic_outline_do_not_disturb_on_24,
        contentDescription = stringResource(SKRes.string.decrease),
        colors = IconButtonDefaults.iconButtonColors(contentColor = color),
        enabled = amount > minimum,
        onClick = {
            onAmountChange((amount - 1).coerceAtLeast(minimum))
        },
    )
    // Amount
    BasicTextField(
        modifier = Modifier
            .width(48.dp)
            .onFocusChanged {
                if (!it.isFocused) {
                    onAmountChange(amountText.toIntOrNull() ?: minimum)
                }
            },
        value = amountText,
        onValueChange = { amountText = it },
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge
            .copy(
                color = color,
                textAlign = TextAlign.Center,
            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
    )
    // +
    AppIconButton(
        iconRes = SKRes.drawable.ic_outline_add_circle_24,
        contentDescription = stringResource(SKRes.string.increase),
        colors = IconButtonDefaults.iconButtonColors(contentColor = color),
        enabled = amount < maximum,
        onClick = {
            onAmountChange((amount + 1).coerceAtMost(maximum))
        },
    )
}


// =========================================
// Previews
// =========================================
@Preview
@Composable
private fun AppAmountSelectorPreview() {
    Surface {
        AppAmountSelector(
            amount = 42,
            onAmountChange = {},
        )
    }
}