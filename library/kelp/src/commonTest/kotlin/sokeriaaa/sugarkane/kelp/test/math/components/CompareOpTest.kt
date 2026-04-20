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

import sokeriaaa.sugarkane.kelp.math.components.CompareOp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CompareOpTest {

    // --- Integer comparisons ---

    @Test
    fun `GT should return true when value1 is greater`() {
        assertTrue(CompareOp.GT.compare(5, 3))
        assertFalse(CompareOp.GT.compare(3, 5))
        assertFalse(CompareOp.GT.compare(3, 3))
    }

    @Test
    fun `GTEQ should handle equality`() {
        assertTrue(CompareOp.GTEQ.compare(5, 3))
        assertTrue(CompareOp.GTEQ.compare(3, 3))
        assertFalse(CompareOp.GTEQ.compare(2, 3))
    }

    @Test
    fun `LT should return true when value1 is smaller`() {
        assertTrue(CompareOp.LT.compare(3, 5))
        assertFalse(CompareOp.LT.compare(5, 3))
        assertFalse(CompareOp.LT.compare(3, 3))
    }

    @Test
    fun `LTEQ should handle equality`() {
        assertTrue(CompareOp.LTEQ.compare(3, 5))
        assertTrue(CompareOp.LTEQ.compare(3, 3))
        assertFalse(CompareOp.LTEQ.compare(5, 3))
    }

    @Test
    fun `EQ should only return true for equal values`() {
        assertTrue(CompareOp.EQ.compare(3, 3))
        assertFalse(CompareOp.EQ.compare(3, 4))
    }

    @Test
    fun `NEQ should return true for non-equal values`() {
        assertTrue(CompareOp.NEQ.compare(3, 4))
        assertFalse(CompareOp.NEQ.compare(3, 3))
    }

    // --- Generic type support ---

    @Test
    fun `Comparator should work with strings`() {
        assertTrue(CompareOp.GT.compare("b", "a"))
        assertTrue(CompareOp.LT.compare("a", "b"))
        assertTrue(CompareOp.EQ.compare("test", "test"))
    }

    @Test
    fun `Comparator should work with floats`() {
        assertTrue(CompareOp.GTEQ.compare(3.5f, 3.5f))
        assertTrue(CompareOp.LT.compare(1.2f, 2.3f))
    }

    // --- Alias tests ---

    @Test
    fun `Aliases should map to correct enum values`() {
        assertEquals(CompareOp.GT, CompareOp.GREATER)
        assertEquals(CompareOp.GTEQ, CompareOp.GREATER_EQUAL)
        assertEquals(CompareOp.LT, CompareOp.LESS)
        assertEquals(CompareOp.LTEQ, CompareOp.LESS_EQUAL)
        assertEquals(CompareOp.EQ, CompareOp.EQUALS)
        assertEquals(CompareOp.NEQ, CompareOp.NOT_EQUALS)
    }

    // --- Edge cases ---

    @Test
    fun `Equal values across all comparators`() {
        val a = 5

        assertFalse(CompareOp.GT.compare(a, a))
        assertTrue(CompareOp.GTEQ.compare(a, a))
        assertFalse(CompareOp.LT.compare(a, a))
        assertTrue(CompareOp.LTEQ.compare(a, a))
        assertTrue(CompareOp.EQ.compare(a, a))
        assertFalse(CompareOp.NEQ.compare(a, a))
    }
}