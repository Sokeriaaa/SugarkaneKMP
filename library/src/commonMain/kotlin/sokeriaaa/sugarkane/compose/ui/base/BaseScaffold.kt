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
package sokeriaaa.sugarkane.compose.ui.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import sokeriaaa.sugarkane.compose.mvi.BaseViewModel

/**
 * Base Scaffold with [BaseViewModel].
 */
@Composable
fun <VM : BaseViewModel> BaseScaffold(
    modifier: Modifier = Modifier,
    viewModel: VM,
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    // Listen to the snack bar intent.
    LaunchedEffect(Unit) {
        viewModel.snackBarIntents.collect { intent ->
            snackBarHostState.showSnackbar(intent.message)
        }
    }
    // Scaffold
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        if (viewModel.isLoading) {
            // Show a loading dialog.
            Dialog(onDismissRequest = {}) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(all = 16.dp),
                )
            }
        }
        content(it)
    }
}