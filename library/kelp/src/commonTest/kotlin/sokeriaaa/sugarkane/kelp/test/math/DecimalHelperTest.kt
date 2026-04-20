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
package sokeriaaa.sugarkane.kelp.test.math

import sokeriaaa.sugarkane.kelp.math.DecimalHelper.toPrecision
import kotlin.test.Test
import kotlin.test.assertEquals

class DecimalHelperTest {

    @Test
    fun `Float toPrecision should format correctly`() {
        assertEquals("12.35", 12.3456f.toPrecision(2))
        assertEquals("12.30", 12.3f.toPrecision(2))
        assertEquals("0.008", 0.0078f.toPrecision(3))
    }

    @Test
    fun `Double toPrecision should delegate to float implementation`() {
        assertEquals("123.457", 123.456789.toPrecision(3))
    }

    @Test
    fun `toPrecision with zero or negative precision should return integer string`() {
        assertEquals("13", 12.78f.toPrecision(0))
        assertEquals("10", 12.78f.toPrecision(-1))
    }

    @Test
    fun `toPrecision should handle small numbers with leading zeros`() {
        // Verifies the padStart logic
        assertEquals("0.01", 0.012f.toPrecision(2))
        assertEquals("1.005", 1.005f.toPrecision(3))
    }
}