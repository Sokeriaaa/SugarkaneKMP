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
package sokeriaaa.sugarkane.kelp.helper

import kotlin.math.pow

/**
 * Helper functions for working with decimals.
 */
object DecimalHelper {

    /**
     * Convert a [Float] to a string with the given number of decimal places.
     *
     * @param precision The number of decimal places to include.
     */
    fun Float.toPrecision(precision: Int): String {
        if (precision <= 0) {
            return this.toInt().toString()
        }
        val integerDigits = this.toInt()
        val digits = 10f.pow(precision).toInt()
        val floatDigits = (this * digits).toInt() % digits
        return "$integerDigits.${floatDigits.toString().padStart(precision, padChar = '0')}"
    }

    /**
     * Convert a [Double] to a string with the given number of decimal places.
     *
     * @param precision The number of decimal places to include.
     */
    fun Double.toPrecision(precision: Int): String {
        return this.toFloat().toPrecision(precision)
    }
}