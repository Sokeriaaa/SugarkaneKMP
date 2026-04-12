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
package sokeriaaa.sugarkane.compose.mvi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import sokeriaaa.sugarkane.compose.mvi.intent.BaseIntent
import sokeriaaa.sugarkane.compose.mvi.intent.CommonIntent

/**
 * Base class for all ViewModels.
 */
abstract class BaseViewModel : ViewModel() {
    var isLoading: Boolean by mutableStateOf(false)
        private set

    private val _snackBarIntents = MutableSharedFlow<CommonIntent.ShowSnackBar>()

    // SnackBar flow.
    val snackBarIntents = _snackBarIntents.asSharedFlow()

    open fun onIntent(intent: BaseIntent) {
        when (intent) {
            CommonIntent.ShowLoading -> isLoading = true
            CommonIntent.HideLoading -> isLoading = false
            is CommonIntent.ShowSnackBar -> viewModelScope.launch {
                _snackBarIntents.emit(intent)
            }
        }
    }
}