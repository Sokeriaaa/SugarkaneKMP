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
package sokeriaaa.sugarkane.kelp.test.text.dingbats

import sokeriaaa.sugarkane.kelp.text.dingbats.MathematicalAlphanumericSymbolsStyle
import kotlin.test.Test
import kotlin.test.assertEquals

class MathematicalAlphanumericSymbolsStyleTest {

    @Test
    fun testBoldTransformation() {
        val style = MathematicalAlphanumericSymbolsStyle.BOLD
        // Latin and Digits
        assertEquals("𝐀", style.convertChar('A'))
        assertEquals("𝐚", style.convertChar('a'))
        assertEquals("𝟎", style.convertChar('0'))
        // Greek
        assertEquals("𝚨", style.convertChar('Α'))
        assertEquals("𝛂", style.convertChar('α'))
        // Exception
        assertEquals("𝛛", style.convertChar('∂'))
    }

    @Test
    fun testItalicExceptions() {
        val style = MathematicalAlphanumericSymbolsStyle.ITALIC
        // 'h' is mapped to Letterlike Symbols ℎ
        assertEquals("ℎ", style.convertChar('h'))
        assertEquals("𝐴", style.convertChar('A'))
    }

    @Test
    fun testDoubleStruckExceptions() {
        val style = MathematicalAlphanumericSymbolsStyle.DOUBLE_STRUCK
        // Many uppercase letters in Double Struck are in the Letterlike Symbols block
        assertEquals("ℂ", style.convertChar('C'))
        assertEquals("ℕ", style.convertChar('N'))
        assertEquals("ℝ", style.convertChar('R'))
        // Non-exception
        assertEquals("𝔸", style.convertChar('A'))
        assertEquals("𝕒", style.convertChar('a'))
    }

    @Test
    fun testScriptAndFraktur() {
        assertEquals("ℬ", MathematicalAlphanumericSymbolsStyle.SCRIPT.convertChar('B'))
        assertEquals("ℭ", MathematicalAlphanumericSymbolsStyle.FRAKTUR.convertChar('C'))
    }

    @Test
    fun testFullStringConversion() {
        val style = MathematicalAlphanumericSymbolsStyle.BOLD
        val input = "Ab1!"
        assertEquals("𝐀𝐛𝟏!", style.convert(input))
    }

    @Test
    fun testNumberConversions() {
        val style = MathematicalAlphanumericSymbolsStyle.MONOSPACE
        assertEquals("𝟷𝟸𝟹", style.convert(123))
        assertEquals("𝟺𝟻.𝟼", style.convert(45.6))
    }

    @Test
    fun testUnmappedCharacters() {
        val style = MathematicalAlphanumericSymbolsStyle.SCRIPT
        // SCRIPT does not have digitStart defined
        assertEquals("1", style.convertChar('1'))
        // Characters outside A-Z, a-z
        assertEquals("!", style.convertChar('!'))
        assertEquals(" ", style.convertChar(' '))
    }

    @Test
    fun testGreekRange() {
        val style = MathematicalAlphanumericSymbolsStyle.SANS_SERIF_BOLD
        // Start of range
        assertEquals("𝝖", style.convertChar('Α'))
        // End of range
        assertEquals("𝞈", style.convertChar('ω'))
    }
}
