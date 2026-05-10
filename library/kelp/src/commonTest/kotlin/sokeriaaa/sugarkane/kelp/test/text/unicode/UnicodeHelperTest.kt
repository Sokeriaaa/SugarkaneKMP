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
package sokeriaaa.sugarkane.kelp.test.text.unicode

import sokeriaaa.sugarkane.kelp.text.unicode.UnicodeHelper
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class UnicodeHelperTest {
    @Test
    fun `charToString converts code points correctly`() {
        // BMP character: 'A'
        assertEquals("A", UnicodeHelper.charToString(0x41))
        // BMP character: '€'
        assertEquals("€", UnicodeHelper.charToString(0x20AC))
        // Supplementary character: '😀' (U+1F600)
        assertEquals("😀", UnicodeHelper.charToString(0x1F600))
    }

    @Test
    fun `charToUtf16 handles single units and surrogate pairs`() {
        // Basic ASCII
        assertContentEquals(intArrayOf(0x0041), UnicodeHelper.charToUtf16(0x41))
        // Supplementary character (Grinning Face)
        // High surrogate: 0xD83D, Low surrogate: 0xDE00
        val expected = intArrayOf(0xD83D, 0xDE00)
        assertContentEquals(expected, UnicodeHelper.charToUtf16(0x1F600))
    }

    @Test
    fun `charToUtf8 handles 1, 2, 3, and 4 byte sequences`() {
        // 1-byte: 'A' (U+0041)
        assertContentEquals(intArrayOf(0x41), UnicodeHelper.charToUtf8(0x41))
        // 2-byte: '¢' (U+00A2) -> 0xC2 0xA2
        assertContentEquals(intArrayOf(0xC2, 0xA2), UnicodeHelper.charToUtf8(0xA2))
        // 3-byte: '€' (U+20AC) -> 0xE2 0x82 0xAC
        assertContentEquals(intArrayOf(0xE2, 0x82, 0xAC), UnicodeHelper.charToUtf8(0x20AC))
        // 4-byte: '😀' (U+1F600) -> 0xF0 0x9F 0x98 0x80
        assertContentEquals(intArrayOf(0xF0, 0x9F, 0x98, 0x80), UnicodeHelper.charToUtf8(0x1F600))
    }

    @Test
    fun `charToUtf8 returns empty array for invalid code points`() {
        // Out of range high
        assertContentEquals(intArrayOf(), UnicodeHelper.charToUtf8(0x110000))
        // Negative range
        assertContentEquals(intArrayOf(), UnicodeHelper.charToUtf8(-1))
    }
}