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
package sokeriaaa.sugarkane.kelp.random

import kotlin.random.Random

object RandomHelper {
    /**
     * Returns `true` with chance of [success]/[base], otherwise `false`.
     */
    fun chance(success: Int, base: Int = 1, random: Random = Random.Default): Boolean =
        random.nextInt(base) < success

    /**
     * Returns `true` with chance of [success]/[base], otherwise `false`.
     */
    fun chance(success: Long, base: Long = 1L, random: Random = Random.Default): Boolean =
        random.nextLong(base) < success

    /**
     * Returns `true` with chance of [success]/[base], otherwise `false`.
     */
    fun chance(success: Double, base: Double = 1.0, random: Random = Random.Default): Boolean =
        random.nextDouble() * base < success

    /**
     * Returns `true` with chance of [success]/[base], otherwise `false`.
     */
    fun chance(success: Float, base: Float = 1F, random: Random = Random.Default): Boolean =
        random.nextFloat() * base < success

    /**
     * Run [ifSuccess] with chance of [success]/[base].
     */
    inline fun withChance(
        success: Int,
        base: Int = 1,
        random: Random = Random.Default,
        ifSuccess: () -> Unit
    ) {
        if (chance(success = success, base = base, random = random)) {
            ifSuccess()
        }
    }

    /**
     * Run [ifSuccess] with chance of [success]/[base].
     */
    inline fun withChance(
        success: Long,
        base: Long = 1L,
        random: Random = Random.Default,
        ifSuccess: () -> Unit
    ) {
        if (chance(success = success, base = base, random = random)) {
            ifSuccess()
        }
    }

    /**
     * Run [ifSuccess] with chance of [success]/[base].
     */
    inline fun withChance(
        success: Double,
        base: Double = 1.0,
        random: Random = Random.Default,
        ifSuccess: () -> Unit
    ) {
        if (chance(success = success, base = base, random = random)) {
            ifSuccess()
        }
    }
}