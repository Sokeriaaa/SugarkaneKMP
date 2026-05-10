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
package sokeriaaa.sugarkane.kelp.text.unicode

/**
 * Helper functions for Unicode converting.
 */
object UnicodeHelper {
    /**
     * Convert a code point to character
     */
    fun charToString(
        codePoint: Int,
    ): String = if (codePoint < 0x10000) {
        codePoint.toChar().toString()
    } else {
        val surrogate = codePoint - 0x10000
        val high = (0xd800 + (surrogate shr 10)).toChar()
        val low = (0xdc00 + (surrogate and 0x3ff)).toChar()
        charArrayOf(high, low).concatToString()
    }

    /**
     * Convert a code point to UTF-16
     */
    fun charToUtf16(
        codePoint: Int,
    ): IntArray = if (codePoint < 0x10000) {
        intArrayOf(codePoint)
    } else {
        val surrogate = codePoint - 0x10000
        val high = (0xd800 + (surrogate shr 10))
        val low = (0xdc00 + (surrogate and 0x3ff))
        intArrayOf(high, low)
    }

    /**
     * Convert a code point to UTF-8
     */
    fun charToUtf8(
        codePoint: Int,
    ): IntArray = when (codePoint) {
        in 0x0..0x7f -> intArrayOf(codePoint)
        in 0x80..0x7ff -> intArrayOf(
            0xc0 or (codePoint shr 6),
            0x80 or (codePoint and 0x3f),
        )

        in 0x800..0xffff -> intArrayOf(
            0xe0 or (codePoint shr 12),
            0x80 or ((codePoint shr 6) and 0x3f),
            0x80 or (codePoint and 0x3f),
        )

        in 0x10000..0x10ffff -> intArrayOf(
            0xf0 or (codePoint shr 18),
            0x80 or ((codePoint shr 12) and 0x3f),
            0x80 or ((codePoint shr 6) and 0x3f),
            0x80 or (codePoint and 0x3F),
        )

        else -> intArrayOf()
    }
}