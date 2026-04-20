/**
 * Copyright (C) 2025 Sokeriaaa
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
package sokeriaaa.sugarkane.kelp.math.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.sugarkane.kelp.math.DecimalHelper.toPrecision

/**
 * Formatter for displaying the values.
 */
@Serializable
sealed interface Formatter {
    @Serializable
    @SerialName("Integer")
    data object Integer : Formatter {
        override fun format(value: Float): String {
            return value.toInt().toString()
        }
    }

    @Serializable
    @SerialName("Percent")
    data class Percent(val decimal: Int) : Formatter {
        override fun format(value: Float): String {
            return (value * 100).toPrecision(decimal) + "%"
        }
    }

    @Serializable
    @SerialName("Decimal")
    data class Decimal(val decimal: Int) : Formatter {
        override fun format(value: Float): String {
            return value.toPrecision(decimal)
        }
    }

    fun format(value: Float): String
}