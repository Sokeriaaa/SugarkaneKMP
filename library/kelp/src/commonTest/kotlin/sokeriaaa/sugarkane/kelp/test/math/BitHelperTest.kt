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

import sokeriaaa.sugarkane.kelp.math.BitHelper.bitToBoolean
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BitHelperTest {
    @Test
    fun `Int bitToBoolean should detect set and unset bits`() {
        // Binary: 0101 (decimal 5)
        val value = 5

        assertTrue(value.bitToBoolean(0), "Bit 0 should be true")
        assertFalse(value.bitToBoolean(1), "Bit 1 should be false")
        assertTrue(value.bitToBoolean(2), "Bit 2 should be true")

        // Check sign bit (31) for a negative number
        assertTrue((-1).bitToBoolean(31), "Sign bit of -1 should be true")
    }

    @Test
    fun `Long bitToBoolean should handle large bit indices`() {
        // Binary: 1 at position 40
        val value = 1L shl 40

        assertTrue(value.bitToBoolean(40))
        assertFalse(value.bitToBoolean(39))
        assertFalse(value.bitToBoolean(0))
    }

    @Test
    fun `bitToBoolean returns false for bits outside the set range`() {
        val value = 1 // Binary: 0001
        assertFalse(value.bitToBoolean(10), "Higher bits should be false")
    }
}