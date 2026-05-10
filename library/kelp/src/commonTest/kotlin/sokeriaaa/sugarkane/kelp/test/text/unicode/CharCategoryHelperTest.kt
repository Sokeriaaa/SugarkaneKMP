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

import sokeriaaa.sugarkane.kelp.text.unicode.CharCategoryHelper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class CharCategoryHelperTest {
    @Test
    fun `getCategoryByCodeOrNull returns correct category for valid codes`() {
        // "Lu" is the code for Uppercase Letter
        assertEquals(
            expected = CharCategory.UPPERCASE_LETTER,
            actual = CharCategoryHelper.getCategoryByCodeOrNull("Lu"),
        )

        // "Nd" is the code for Decimal Digit Number
        assertEquals(
            expected = CharCategory.DECIMAL_DIGIT_NUMBER,
            actual = CharCategoryHelper.getCategoryByCodeOrNull("Nd"),
        )
    }

    @Test
    fun `getCategoryByCodeOrNull returns null for invalid codes`() {
        assertNull(CharCategoryHelper.getCategoryByCodeOrNull("InvalidCode"))
        assertNull(CharCategoryHelper.getCategoryByCodeOrNull(""))
    }

    @Test
    fun `getCategoryByCode returns correct category for valid codes`() {
        val result = CharCategoryHelper.getCategoryByCode("Lu")
        assertEquals(CharCategory.UPPERCASE_LETTER, result)
    }

    @Test
    fun `getCategoryByCode throws IllegalArgumentException for invalid codes`() {
        val invalidCode = "InvalidCode"
        val exception = assertFailsWith<IllegalArgumentException> {
            CharCategoryHelper.getCategoryByCode(invalidCode)
        }
        assertEquals("Invalid code: $invalidCode", exception.message)
    }
}