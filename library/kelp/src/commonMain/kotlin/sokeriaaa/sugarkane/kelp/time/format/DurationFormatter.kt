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
package sokeriaaa.sugarkane.kelp.time.format

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

/**
 * Duration formatter with customizable unit symbols.
 *
 * @param maxUnit The max unit for displaying.
 * @param maxItems The max item count for the units. Units with a zero value also occupy a slot.
 * @param includeZeroes Display items with a zero value or not.
 * @param symbols Symbols used for displaying units.
 */
data class DurationFormatter(
    val maxUnit: DurationUnit = DurationUnit.DAYS,
    val maxItems: Int = 2,
    val includeZeroes: Boolean = false,
    val symbols: Symbols = Symbols.Default,
) {
    /**
     * Unit symbols for different duration units.
     */
    interface Symbols {
        val days: String
        val hours: String
        val minutes: String
        val seconds: String
        val millis: String
        val micros: String
        val nanos: String
        val spacer: String

        operator fun get(unit: DurationUnit): String = when (unit) {
            DurationUnit.DAYS -> days
            DurationUnit.HOURS -> hours
            DurationUnit.MINUTES -> minutes
            DurationUnit.SECONDS -> seconds
            DurationUnit.MILLISECONDS -> millis
            DurationUnit.MICROSECONDS -> micros
            DurationUnit.NANOSECONDS -> nanos
            else -> unit.name.lowercase()
        }

        companion object {
            operator fun invoke(
                days: String = "d",
                hours: String = "h",
                minutes: String = "m",
                seconds: String = "s",
                millis: String = "ms",
                micros: String = "us",
                nanos: String = "ns",
                spacer: String = " ",
            ): Symbols = SimpleSymbols(
                days = days,
                hours = hours,
                minutes = minutes,
                seconds = seconds,
                millis = millis,
                micros = micros,
                nanos = nanos,
                spacer = spacer,
            )

            val Default = Symbols()
        }
    }

    private data class SimpleSymbols(
        override val days: String,
        override val hours: String,
        override val minutes: String,
        override val seconds: String,
        override val millis: String,
        override val micros: String,
        override val nanos: String,
        override val spacer: String,
    ) : Symbols

    init {
        require(maxItems > 0) {
            "maxItems must be greater than 0, current: $maxItems"
        }
    }

    private val unitData: List<Pair<Long, String>> =
        DurationUnit.entries.reversed()
            .let { entries ->
                val startIndex = entries.indexOf(maxUnit).coerceAtLeast(0)
                entries.drop(startIndex).take(maxItems)
            }
            .map { unit ->
                unit.toNanos() to symbols[unit]
            }

    /**
     * Formats the given [millis] as a duration string.
     */
    fun format(millis: Long): String = format(millis.milliseconds)

    /**
     * Formats the given [duration] as a string.
     */
    fun format(duration: Duration): String = buildString {
        if (duration == Duration.ZERO || unitData.isEmpty()) {
            append("0")
            append(unitData.lastOrNull()?.second ?: "")
            return@buildString
        }

        if (duration.isNegative()) {
            append("-")
        }

        var remainingNanos = duration.absoluteValue.inWholeNanoseconds
        var appendedCount = 0

        for ((unitNanos, symbol) in unitData) {
            if (unitNanos == 0L) continue

            val current = remainingNanos / unitNanos
            if (current != 0L || (includeZeroes && appendedCount > 0)) {
                if (appendedCount > 0) {
                    append(symbols.spacer)
                }
                append(current)
                append(symbol)
                appendedCount++
            }
            remainingNanos %= unitNanos
        }

        if (appendedCount == 0) {
            if (isNotEmpty() && this[0] == '-') setLength(0)
            append("0")
            append(unitData.last().second)
        }
    }

    private fun DurationUnit.toNanos(): Long = when (this) {
        DurationUnit.NANOSECONDS -> 1L
        DurationUnit.MICROSECONDS -> 1_000L
        DurationUnit.MILLISECONDS -> 1_000_000L
        DurationUnit.SECONDS -> 1_000_000_000L
        DurationUnit.MINUTES -> 60_000_000_000L
        DurationUnit.HOURS -> 3_600_000_000_000L
        DurationUnit.DAYS -> 86_400_000_000_000L
        else -> 0L
    }

    companion object
}
