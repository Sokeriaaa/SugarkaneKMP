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
package sokeriaaa.sugarkane.kelp.time

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object TimeHelper {

    const val ONE_HOUR = 1000L * 60L * 60L
    const val ONE_DAY = ONE_HOUR * 24L
    const val ONE_WEEK = ONE_DAY * 7L

    @OptIn(ExperimentalTime::class)
    fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()

    /**
     * Get the time millis at 0:00 UTC the next day.
     */
    fun nextDay(now: Long = currentTimeMillis()): Long =
        ((now / ONE_DAY) + 1) * ONE_DAY

    /**
     * Get the time millis at 0:00 UTC next Sunday.
     * (January 1, 1970, was a Thursday.)
     */
    fun nextSunday(now: Long = currentTimeMillis()): Long {
        val daysSinceEpoch = now / ONE_DAY
        val dayOfWeek = (daysSinceEpoch + 4) % 7
        val daysUntilSunday = (7 - dayOfWeek) % 7
        return (daysSinceEpoch + daysUntilSunday) * ONE_DAY
    }

    fun millisToString(millis: Long): String {
        val instant = Instant.fromEpochMilliseconds(millis)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return localDateTime.toString()
    }

}