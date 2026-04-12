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
package sokeriaaa.sugarkane.kelp.test.helper

import kotlin.random.Random

/**
 * A fake random class extends [Random] for testing.
 *
 * [values] will return one by one in all "next" functions instead of a random value.
 */
class FakeRandom(private val values: List<Number>) : Random() {

    constructor(value: Number) : this(listOf(value))
    constructor(vararg values: Number) : this(values.toList())

    private var index = 0

    private fun next(): Number {
        if (index >= values.size)
            error("FakeRandom exhausted")
        return values[index++]
    }

    override fun nextBits(bitCount: Int): Int {
        // Use nextInt() then mask out only the requested bits.
        val value = next().toInt()
        return value and ((1 shl bitCount) - 1)
    }

    override fun nextInt(from: Int, until: Int): Int = next().toInt()
    override fun nextLong(from: Long, until: Long): Long = next().toLong()
    override fun nextDouble(): Double = next().toDouble()
    override fun nextFloat(): Float = next().toFloat()
}
