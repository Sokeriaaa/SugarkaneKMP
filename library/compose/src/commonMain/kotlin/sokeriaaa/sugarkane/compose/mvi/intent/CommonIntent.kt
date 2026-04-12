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
package sokeriaaa.sugarkane.compose.mvi.intent

/**
 * Common intents for all ViewModels.
 */
sealed class CommonIntent : BaseIntent {
    /**
     * A common intent for showing a loading indicator.
     */
    data object ShowLoading : CommonIntent()

    /**
     * A common intent for hiding the loading indicator.
     */
    data object HideLoading : CommonIntent()

    /**
     * A common intent for showing a snack bar.
     */
    data class ShowSnackBar(val message: String) : CommonIntent()

    /**
     * A common intent for refreshing data.
     */
    data object Refresh : CommonIntent()
}