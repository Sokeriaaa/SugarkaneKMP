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

import sokeriaaa.sugarkane.kelp.helper.JsonHelper
import sokeriaaa.sugarkane.kelp.helper.JsonHelper.toJsonString
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonHelperTest {
    @Test
    fun `encodeToJsonString() encodes correctly`() {
        val data = JsonTestData("hello", 123)

        val json = JsonHelper.encodeToJsonString(data)

        assertEquals("""{"name":"hello","value":123}""", json)
    }

    @Test
    fun `decodeFromString() decodes correctly`() {
        val json = """{"name":"world","value":456}"""

        val data = JsonHelper.decodeFromString<JsonTestData>(json)

        assertEquals("world", data.name)
        assertEquals(456, data.value)
    }

    @Test
    fun `extension toJsonString() encodes correctly`() {
        val data = JsonTestData("foo", 999)

        val json = data.toJsonString()

        assertEquals("""{"name":"foo","value":999}""", json)
    }

    @Test
    fun `decodeFromString() ignores unknown keys`() {
        val json = """
        {
            "name": "test",
            "value": 42,
            "extra": "SHOULD_BE_IGNORED"
        }
        """.trimIndent()

        val data = JsonHelper.decodeFromString<JsonTestData>(json)

        assertEquals("test", data.name)
        assertEquals(42, data.value)
    }
}