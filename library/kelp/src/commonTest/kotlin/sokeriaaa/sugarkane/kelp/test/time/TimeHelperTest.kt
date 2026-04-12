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
package sokeriaaa.sugarkane.kelp.test.time

import sokeriaaa.sugarkane.kelp.time.TimeHelper
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeHelperTest {
    @Test
    fun `nextDay should return next midnight UTC`() {
        // 1970-01-01 10:00 UTC
        val now = 10 * TimeHelper.ONE_HOUR
        val expected = TimeHelper.ONE_DAY
        val result = TimeHelper.nextDay(now)

        assertEquals(expected, result)
    }

    @Test
    fun `nextDay at midnight should return following day`() {
        // 1970-01-01 00:00 UTC
        val now = 0L
        val expected = TimeHelper.ONE_DAY
        val result = TimeHelper.nextDay(now)

        assertEquals(expected, result)
    }

    @Test
    fun `nextSunday from Thursday should return same week Sunday`() {
        // 1970-01-01 Thursday 00:00 UTC
        val now = 0L
        // Sunday = Jan 4, 1970
        val expected = 3 * TimeHelper.ONE_DAY
        val result = TimeHelper.nextSunday(now)

        assertEquals(expected, result)
    }

    @Test
    fun `nextSunday from Saturday should return next day`() {
        // Saturday Jan 3, 1970 12:00 UTC
        val now = 2 * TimeHelper.ONE_DAY + 12 * TimeHelper.ONE_HOUR
        val expected = 3 * TimeHelper.ONE_DAY
        val result = TimeHelper.nextSunday(now)

        assertEquals(expected, result)
    }

    @Test
    fun `nextSunday from Sunday midnight should return same Sunday`() {
        // Sunday Jan 4, 1970 00:00 UTC
        val now = 3 * TimeHelper.ONE_DAY
        val expected = 3 * TimeHelper.ONE_DAY
        val result = TimeHelper.nextSunday(now)

        assertEquals(expected, result)
    }
}