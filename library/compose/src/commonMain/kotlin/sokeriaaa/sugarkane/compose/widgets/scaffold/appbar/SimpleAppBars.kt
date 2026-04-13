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
package sokeriaaa.sugarkane.compose.widgets.scaffold.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import sokeriaaa.sugarkane.compose.widgets.button.AppBackIconButton

@ExperimentalMaterial3Api
@Composable
fun SimpleTopAppBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBack: () -> Unit,
) = TopAppBar(
    title = { Text(text = title) },
    navigationIcon = { AppBackIconButton(onClick = onBack) },
    actions = actions,
)

@ExperimentalMaterial3Api
@Composable
fun SimpleTopAppBar(
    titleRes: StringResource,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBack: () -> Unit,
) = SimpleTopAppBar(
    title = stringResource(titleRes),
    onBack = onBack,
    actions = actions,
)

@ExperimentalMaterial3Api
@Composable
fun SimpleCenterAlignedTopAppBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBack: () -> Unit,
) = CenterAlignedTopAppBar(
    title = { Text(text = title) },
    navigationIcon = { AppBackIconButton(onClick = onBack) },
    actions = actions,
)

@ExperimentalMaterial3Api
@Composable
fun SimpleCenterAlignedTopAppBar(
    titleRes: StringResource,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBack: () -> Unit,
) = SimpleCenterAlignedTopAppBar(
    title = stringResource(titleRes),
    onBack = onBack,
    actions = actions,
)