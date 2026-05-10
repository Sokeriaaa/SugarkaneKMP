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
package sokeriaaa.sugarkane.kelp.text.dingbats

import sokeriaaa.sugarkane.kelp.text.unicode.UnicodeHelper

/**
 * Character styles.
 *
 * @param upperLatinStart Unicode start for A-Z.
 * @param lowerLatinStart Unicode start for a-z.
 * @param upperGreekStart Unicode start for Α-Ω.
 * @param lowerGreekStart Unicode start for α-ω
 * @param digitStart Unicode start for 0-9.
 * @param exceptions Characters not following sequential offsets.
 *                    (Mainly for characters mapped in the "Letterlike Symbols" block and
 *                    some Greek symbols.)
 */
enum class MathematicalAlphanumericSymbolsStyle(
    val upperLatinStart: Int? = null,
    val lowerLatinStart: Int? = null,
    val upperGreekStart: Int? = null,
    val lowerGreekStart: Int? = null,
    val digitStart: Int? = null,
    val exceptions: Map<Char, String> = emptyMap(),
) {
    /**
     * 𝐀 𝐚 𝟎 𝚨 𝛂
     */
    BOLD(
        upperLatinStart = 0x1D400,
        lowerLatinStart = 0x1D41A,
        digitStart = 0x1D7CE,
        upperGreekStart = 0x1D6A8,
        lowerGreekStart = 0x1D6C2,
        exceptions = mapOf(
            // Greek symbols
            '∂' to "𝛛",
            'ϵ' to "𝛜",
            'ϑ' to "𝛝",
            'ϰ' to "𝛞",
            'ϕ' to "𝛟",
            'ϱ' to "𝛠",
            'ϖ' to "𝛡",
            'Ϝ' to "𝟊",
            'ϝ' to "𝟋",
        ),
    ),

    /**
     * 𝐴 𝑎 𝛢 𝛼
     */
    ITALIC(
        upperLatinStart = 0x1D434,
        lowerLatinStart = 0x1D44E,
        digitStart = null,
        upperGreekStart = 0x1D6E2,
        lowerGreekStart = 0x1D6FC,
        exceptions = mapOf(
            // Lowercase
            'h' to "ℎ",
            // Greek symbols
            '∂' to "𝜕",
            'ϵ' to "𝜖",
            'ϑ' to "𝜗",
            'ϰ' to "𝜘",
            'ϕ' to "𝜙",
            'ϱ' to "𝜚",
            'ϖ' to "𝜛",
        ),
    ),

    /**
     * 𝑨 𝒂 𝜜 𝜶
     */
    BOLD_ITALIC(
        upperLatinStart = 0x1D468,
        lowerLatinStart = 0x1D482,
        digitStart = null,
        upperGreekStart = 0x1D71C,
        lowerGreekStart = 0x1D736,
        exceptions = mapOf(
            '∂' to "𝝏",
            'ϵ' to "𝝐",
            'ϑ' to "𝝑",
            'ϰ' to "𝝒",
            'ϕ' to "𝝓",
            'ϱ' to "𝝔",
            'ϖ' to "𝝕",
        ),
    ),

    /**
     * 𝒜 𝒶
     */
    SCRIPT(
        upperLatinStart = 0x1D49C,
        lowerLatinStart = 0x1D4B6,
        digitStart = null,
        exceptions = mapOf(
            // Uppercase
            'B' to "ℬ",
            'E' to "ℰ",
            'F' to "ℱ",
            'H' to "ℋ",
            'I' to "ℐ",
            'L' to "ℒ",
            'M' to "ℳ",
            'R' to "ℛ",
            // Lowercase
            'e' to "ℯ",
            'g' to "ℊ",
            'o' to "ℴ",
        ),
    ),

    /**
     * 𝓐 𝓪
     */
    BOLD_SCRIPT(
        upperLatinStart = 0x1D4D0,
        lowerLatinStart = 0x1D4EA,
    ),

    /**
     * 𝔄 𝔞
     */
    FRAKTUR(
        upperLatinStart = 0x1D504,
        lowerLatinStart = 0x1D51E,
        digitStart = null,
        exceptions = mapOf(
            'C' to "ℭ",
            'H' to "ℌ",
            'I' to "ℑ",
            'R' to "ℜ",
            'Z' to "ℨ",
        ),
    ),

    /**
     * 𝕬 𝖆
     */
    BOLD_FRAKTUR(
        upperLatinStart = 0x1D56C,
        lowerLatinStart = 0x1D586,
    ),

    /**
     * 𝔸 𝕒 𝟘
     */
    DOUBLE_STRUCK(
        upperLatinStart = 0x1D538,
        lowerLatinStart = 0x1D552,
        digitStart = 0x1D7D8,
        exceptions = mapOf(
            'C' to "ℂ",
            'H' to "ℍ",
            'N' to "ℕ",
            'P' to "ℙ",
            'Q' to "ℚ",
            'R' to "ℝ",
            'Z' to "ℤ",
        ),
    ),

    /**
     * 𝖠 𝖺 𝟢 𝝖 𝝰
     */
    SANS_SERIF(
        upperLatinStart = 0x1D5A0,
        lowerLatinStart = 0x1D5BA,
        upperGreekStart = 0x1D756,
        lowerGreekStart = 0x1D770,
        digitStart = 0x1D7E2,
    ),

    /**
     * 𝗔 𝗮 𝟬 𝝠 𝞪
     */
    SANS_SERIF_BOLD(
        upperLatinStart = 0x1D5D4,
        lowerLatinStart = 0x1D5EE,
        upperGreekStart = 0x1D756,
        lowerGreekStart = 0x1D770,
        digitStart = 0x1D7EC,
        exceptions = mapOf(
            '∂' to "𝞉",
            'ϵ' to "𝞊",
            'ϑ' to "𝞋",
            'ϰ' to "𝞌",
            'ϕ' to "𝞍",
            'ϱ' to "𝞎",
            'ϖ' to "𝞏",
        ),
    ),

    /**
     * 𝘈 𝘢 𝞐 𝞪
     */
    SANS_SERIF_ITALIC(
        upperLatinStart = 0x1D608,
        lowerLatinStart = 0x1D622,
        upperGreekStart = 0x1D790,
        lowerGreekStart = 0x1D7AA,
        exceptions = mapOf(
            '∂' to "𝟃",
            'ϵ' to "𝟄",
            'ϑ' to "𝟅",
            'ϰ' to "𝟆",
            'ϕ' to "𝟇",
            'ϱ' to "𝟈",
            'ϖ' to "𝟉",
        ),
    ),

    /**
     * 𝘼 𝙖 𝞪 𝟄
     */
    SANS_SERIF_BOLD_ITALIC(
        upperLatinStart = 0x1D63C,
        lowerLatinStart = 0x1D656,
        upperGreekStart = 0x1D7C2,
        lowerGreekStart = 0x1D7DC,
    ),

    /**
     * 𝙰 𝚊 𝟶
     */
    MONOSPACE(
        upperLatinStart = 0x1D670,
        lowerLatinStart = 0x1D68A,
        digitStart = 0x1D7F6,
    ),
    ;

    fun convert(text: String): String {
        val builder = StringBuilder()
        text.forEach { char ->
            builder.append(convertChar(char))
        }
        return builder.toString()
    }

    fun convert(number: Int): String = convert(number.toString())
    fun convert(number: Long): String = convert(number.toString())
    fun convert(number: Double): String = convert(number.toString())

    fun convertChar(char: Char): String {
        // Exceptions first
        exceptions[char]?.let { return it }

        // Latin uppercase
        if (char in 'A'..'Z' && upperLatinStart != null) {
            val codePoint = upperLatinStart + (char - 'A')
            return UnicodeHelper.charToString(codePoint)
        }

        // Latin lowercase
        if (char in 'a'..'z' && lowerLatinStart != null) {
            val codePoint = lowerLatinStart + (char - 'a')
            return UnicodeHelper.charToString(codePoint)
        }

        // Digits
        if (char in '0'..'9' && digitStart != null) {
            val codePoint = digitStart + (char - '0')
            return UnicodeHelper.charToString(codePoint)
        }

        // Greek uppercase
        if (char in 'Α'..'Ω' && upperGreekStart != null) {
            val codePoint = upperGreekStart + (char - 'Α')
            return UnicodeHelper.charToString(codePoint)
        }

        // Greek lowercase
        if (char in 'α'..'ω' && lowerGreekStart != null) {
            val codePoint = lowerGreekStart + (char - 'α')
            return UnicodeHelper.charToString(codePoint)
        }

        return char.toString()
    }
}