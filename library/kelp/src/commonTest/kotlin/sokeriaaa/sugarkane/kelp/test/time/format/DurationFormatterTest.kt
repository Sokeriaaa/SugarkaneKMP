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
package sokeriaaa.sugarkane.kelp.test.time.format

import sokeriaaa.sugarkane.kelp.time.format.DurationFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class DurationFormatterTest {

    @Test
    fun testDefaultFormatting() {
        val formatter = DurationFormatter()

        // Tests basic formatting with max 2 items (Days and Hours)
        val duration = 2.days + 3.hours + 45.minutes
        assertEquals("2d 3h", formatter.format(duration))
    }

    @Test
    fun testMillisFormatting() {
        val formatter = DurationFormatter()

        // Tests the Long (millisecond) primitive overload
        assertEquals("0h", formatter.format(0L))
        assertEquals("1h", formatter.format(3_600_000L))
    }

    @Test
    fun testNegativeDuration() {
        val formatter = DurationFormatter()
        val duration = -(1.days + 5.hours)

        assertEquals("-1d 5h", formatter.format(duration))
    }

    @Test
    fun testMaxItemsConstraint() {
        val formatter = DurationFormatter(maxItems = 3)
        val duration = 1.days + 2.hours + 30.minutes + 15.seconds

        // Should cap at 3 units: days, hours, minutes
        assertEquals("1d 2h 30m", formatter.format(duration))
    }

    @Test
    fun testMaxUnitConstraint() {
        // Starts processing from Hours instead of Days
        val formatter = DurationFormatter(maxUnit = DurationUnit.HOURS, maxItems = 2)
        val duration = 1.days + 2.hours + 30.minutes

        // 1 day + 2 hours = 26 hours
        assertEquals("26h 30m", formatter.format(duration))
    }

    @Test
    fun testIncludeZeroesTrue() {
        val formatter = DurationFormatter(includeZeroes = true, maxItems = 3)
        val duration = 1.days + 30.minutes

        // Should include the 0h middle tier unit
        assertEquals("1d 0h 30m", formatter.format(duration))
    }

    @Test
    fun testIncludeZeroesFalse() {
        val formatter = DurationFormatter(includeZeroes = false, maxItems = 3)
        val duration = 1.days + 30.minutes

        // Should skip the 0h unit entirely
        assertEquals("1d 30m", formatter.format(duration))
    }

    @Test
    fun testCustomSymbols() {
        val customSymbols = DurationFormatter.Symbols(
            days = " days",
            hours = " hrs",
            spacer = ", "
        )
        val formatter = DurationFormatter(symbols = customSymbols)
        val duration = 2.days + 4.hours

        assertEquals("2 days, 4 hrs", formatter.format(duration))
    }

    @Test
    fun testI18nSymbols() {
        val customSymbols = DurationFormatter.Symbols(
            days = "天",
            hours = "小时",
            spacer = ""
        )
        val formatter = DurationFormatter(symbols = customSymbols)
        val duration = 2.days + 4.hours

        assertEquals("2天4小时", formatter.format(duration))
    }

    @Test
    fun testInvalidMaxItemsThrows() {
        // Validation check for strict init block requirement
        assertFailsWith<IllegalArgumentException> {
            DurationFormatter(maxItems = 0)
        }
    }
}
