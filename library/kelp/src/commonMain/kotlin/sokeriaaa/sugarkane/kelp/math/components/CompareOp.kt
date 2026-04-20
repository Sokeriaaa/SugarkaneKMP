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

import kotlinx.serialization.Serializable

/**
 * Common comparator for components.
 */
@Serializable
enum class CompareOp {
    /**
     * Greater
     */
    GT,

    /**
     * Greater or equals
     */
    GTEQ,

    /**
     * Less
     */
    LT,

    /**
     * Less or equals
     */
    LTEQ,

    /**
     * Equals
     */
    EQ,

    /**
     * Not equals
     */
    NEQ,
    ;

    fun <C : Comparable<C>> compare(value1: C, value2: C): Boolean {
        return when (this) {
            GT -> value1 > value2
            GTEQ -> value1 >= value2
            LT -> value1 < value2
            LTEQ -> value1 <= value2
            EQ -> value1 == value2
            NEQ -> value1 != value2
        }
    }

    companion object {
        // Alias
        val GREATER = GT
        val GREATER_EQUAL = GTEQ
        val LESS = LT
        val LESS_EQUAL = LTEQ
        val EQUALS = EQ
        val NOT_EQUALS = NEQ
    }
}