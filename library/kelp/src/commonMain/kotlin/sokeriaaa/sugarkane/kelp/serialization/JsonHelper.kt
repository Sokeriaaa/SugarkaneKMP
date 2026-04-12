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
package sokeriaaa.sugarkane.kelp.serialization

import kotlinx.serialization.json.Json

object JsonHelper {

    val json: Json = Json { ignoreUnknownKeys = true }

    inline fun <reified T> encodeToJsonString(
        value: T,
    ): String {
        return json.encodeToString(
            value = value,
        )
    }

    inline fun <reified T> decodeFromString(
        string: String,
    ): T {
        return json.decodeFromString(
            string = string,
        )
    }

    inline fun <reified T> T.toJsonString(): String =
        json.encodeToString(value = this)
}