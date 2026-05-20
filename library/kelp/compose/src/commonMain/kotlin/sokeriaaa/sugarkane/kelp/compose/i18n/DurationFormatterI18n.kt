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
package sokeriaaa.sugarkane.kelp.compose.i18n

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import sokeriaaa.sugarkane.kelp.compose.res.SKCRes
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_days_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_hours_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_microseconds_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_milliseconds_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_minutes_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_nanoseconds_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_seconds_short
import sokeriaaa.sugarkane.kelp.compose.res.duration_unit_spacer
import sokeriaaa.sugarkane.kelp.time.TimeHelper
import sokeriaaa.sugarkane.kelp.time.format.DurationFormatter

@Suppress("ComposableNaming")
@Composable
fun DurationFormatter.Companion.I18nSymbols() = DurationFormatter.Symbols(
    days = stringResource(SKCRes.string.duration_unit_days_short),
    hours = stringResource(SKCRes.string.duration_unit_hours_short),
    minutes = stringResource(SKCRes.string.duration_unit_minutes_short),
    seconds = stringResource(SKCRes.string.duration_unit_seconds_short),
    millis = stringResource(SKCRes.string.duration_unit_milliseconds_short),
    micros = stringResource(SKCRes.string.duration_unit_microseconds_short),
    nanos = stringResource(SKCRes.string.duration_unit_nanoseconds_short),
    spacer = stringResource(SKCRes.string.duration_unit_spacer),
)

// ==========================================
// Previews
// ==========================================
@Preview
@Composable
private fun DurationFormatterI18nPreview1() {
    Text(
        DurationFormatter(symbols = DurationFormatter.I18nSymbols())
            .format(millis = TimeHelper.ONE_DAY + TimeHelper.ONE_HOUR)
    )
}

@Preview
@Composable
private fun DurationFormatterI18nPreview2() {
    Text(
        DurationFormatter(symbols = DurationFormatter.I18nSymbols())
            .format(millis = TimeHelper.ONE_DAY)
    )
}

@Preview
@Composable
private fun DurationFormatterI18nPreview3() {
    Text(
        DurationFormatter(includeZeroes = true, symbols = DurationFormatter.I18nSymbols())
            .format(millis = TimeHelper.ONE_DAY)
    )
}