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
package sokeriaaa.sugarkane.kelp.test.math.components

import sokeriaaa.sugarkane.kelp.math.components.Formatter
import kotlin.test.Test
import kotlin.test.assertEquals

class FormatterTest {

    @Test
    fun `Integer formatter should truncate decimal part`() {
        val formatter = Formatter.Integer

        assertEquals("5", formatter.format(5.9f))
        assertEquals("3", formatter.format(3.1f))
        assertEquals("-2", formatter.format(-2.7f))
        assertEquals("0", formatter.format(0.0f))
    }

    @Test
    fun `Percent formatter should multiply by 100 and format with precision`() {
        val formatter = Formatter.Percent(decimal = 2)

        assertEquals("50.00%", formatter.format(0.5f))
        assertEquals("12.34%", formatter.format(0.1234f))
        assertEquals("100.00%", formatter.format(1.0f))
        assertEquals("0.00%", formatter.format(0.0f))
    }

    @Test
    fun `Percent formatter should respect different precision`() {
        val formatter = Formatter.Percent(decimal = 1)

        assertEquals("50.0%", formatter.format(0.5f))
        assertEquals("12.3%", formatter.format(0.1234f))
    }

    @Test
    fun `Decimal formatter should format with given precision`() {
        val formatter = Formatter.Decimal(decimal = 2)

        assertEquals("5.00", formatter.format(5f))
        assertEquals("3.14", formatter.format(3.14159f))
        assertEquals("0.00", formatter.format(0f))
    }

    @Test
    fun `Decimal formatter should respect different precision`() {
        val formatter = Formatter.Decimal(decimal = 3)

        assertEquals("3.142", formatter.format(3.14159f))
        assertEquals("0.123", formatter.format(0.1234f))
    }

    @Test
    fun `Formatters should handle negative values correctly`() {
        val percent = Formatter.Percent(decimal = 2)
        val decimal = Formatter.Decimal(decimal = 2)

        assertEquals("-50.00%", percent.format(-0.5f))
        assertEquals("-3.14", decimal.format(-3.14159f))
    }
}