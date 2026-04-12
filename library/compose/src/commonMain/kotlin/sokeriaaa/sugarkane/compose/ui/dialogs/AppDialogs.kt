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
package sokeriaaa.sugarkane.compose.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sokeriaaa.sugarkane.compose.res.Res
import sokeriaaa.sugarkane.compose.res.cancel
import sokeriaaa.sugarkane.compose.res.ok
import sokeriaaa.sugarkane.compose.ui.widgets.AppTextButton

/**
 * Wrapped [AlertDialog] to reduce nesting.
 */
@Composable
fun AppAlertDialog(
    modifier: Modifier = Modifier,
    iconRes: DrawableResource? = null,
    iconContentDescription: String? = null,
    title: String? = null,
    text: String? = null,
    confirmText: String = stringResource(Res.string.ok),
    cancelText: String? = stringResource(Res.string.cancel),
    onDismiss: () -> Unit,
    onConfirmed: () -> Unit = onDismiss,
    onCanceled: () -> Unit = onDismiss,
) = AppAlertDialog(
    modifier = modifier,
    iconRes = iconRes,
    iconContentDescription = iconContentDescription,
    title = title,
    content = text?.let { @Composable { Text(text = it) } },
    confirmText = confirmText,
    cancelText = cancelText,
    onDismiss = onDismiss,
    onConfirmed = onConfirmed,
    onCanceled = onCanceled,
)

/**
 * Wrapped [AlertDialog] to reduce nesting.
 */
@Composable
fun AppAlertDialog(
    modifier: Modifier = Modifier,
    iconRes: DrawableResource? = null,
    iconContentDescription: String? = null,
    title: String? = null,
    content: (@Composable () -> Unit)?,
    confirmText: String = stringResource(Res.string.ok),
    cancelText: String? = stringResource(Res.string.cancel),
    onDismiss: () -> Unit,
    onConfirmed: () -> Unit = onDismiss,
    onCanceled: () -> Unit = onDismiss,
) = AlertDialog(
    modifier = modifier,
    onDismissRequest = onDismiss,
    icon = iconRes?.let {
        @Composable {
            Icon(
                painter = painterResource(it),
                contentDescription = iconContentDescription,
            )
        }
    },
    title = title?.let { @Composable { Text(text = it) } },
    text = content,
    confirmButton = {
        AppTextButton(
            text = confirmText,
            onClick = onConfirmed,
        )
    },
    dismissButton = cancelText?.let {
        @Composable {
            AppTextButton(
                text = cancelText,
                onClick = onCanceled,
            )
        }
    },
)