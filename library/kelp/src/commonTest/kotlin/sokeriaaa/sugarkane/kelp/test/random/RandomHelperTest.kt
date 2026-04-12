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
package sokeriaaa.sugarkane.kelp.test.random

import sokeriaaa.sugarkane.kelp.random.RandomHelper
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RandomHelperTest {
    @Test
    fun `chance(int) true`() {
        val rng = FakeRandom(0)
        assertTrue(RandomHelper.chance(success = 1, base = 2, random = rng))
    }

    @Test
    fun `chance(int) false`() {
        val rng = FakeRandom(1)
        assertFalse(RandomHelper.chance(success = 1, base = 2, random = rng))
    }

    @Test
    fun `chance(long) true`() {
        val rng = FakeRandom(0L)
        assertTrue(RandomHelper.chance(success = 1L, base = 2L, random = rng))
    }

    @Test
    fun `chance(long) false`() {
        val rng = FakeRandom(1L)
        assertFalse(RandomHelper.chance(success = 1L, base = 2L, random = rng))
    }

    @Test
    fun `chance(double) true`() {
        val rng = FakeRandom(0.2)
        assertTrue(RandomHelper.chance(success = 0.5, base = 1.0, random = rng))
    }

    @Test
    fun `chance(double) false`() {
        val rng = FakeRandom(0.8)
        assertFalse(RandomHelper.chance(success = 0.5, base = 1.0, random = rng))
    }

    @Test
    fun `chance(float) true`() {
        val rng = FakeRandom(0.1f)
        assertTrue(RandomHelper.chance(success = 0.5f, base = 1f, random = rng))
    }

    @Test
    fun `chance(float) false`() {
        val rng = FakeRandom(0.8f)
        assertFalse(RandomHelper.chance(success = 0.5f, base = 1f, random = rng))
    }

    @Test
    fun `withChance runs callback`() {
        var called = false
        val rng = FakeRandom(0)
        RandomHelper.withChance(success = 1, base = 2, random = rng) { called = true }
        assertTrue(called)
    }

    @Test
    fun `withChance does not run callback`() {
        var called = false
        val rng = FakeRandom(1)
        RandomHelper.withChance(success = 1, base = 2, random = rng) { called = true }
        assertFalse(called)
    }
}