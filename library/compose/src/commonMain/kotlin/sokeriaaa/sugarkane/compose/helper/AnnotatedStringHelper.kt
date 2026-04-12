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
package sokeriaaa.sugarkane.compose.helper

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

fun CharSequence.replaceAnnotatedString(
    oldValue: String,
    newValue: AnnotatedString,
): CharSequence {
    val index = this.indexOf(oldValue)
    if (index == -1) {
        return this
    }
    val start = this.subSequence(0, index)
    val end = this.subSequence(index + oldValue.length, this.length)
    return buildAnnotatedString {
        append(start)
        append(newValue)
        append(end)
    }
}

@Preview
@Composable
private fun ReplaceExampleOriginal() {
    Text(
        text = buildAnnotatedString {
            append("Lorem ipsum dolor sit amet, ")
            withStyle(SpanStyle(color = Color.Red)) {
                append("consectetur adipiscing elit.")
            }
        }
    )
}


@Preview
@Composable
private fun ReplaceExample() {
    Text(
        // TODO Optimize code.
        text = buildAnnotatedString {
            append(
                buildAnnotatedString {
                    append("Lorem ipsum dolor sit amet, ")
                    withStyle(SpanStyle(color = Color.Red)) {
                        append("consectetur adipiscing elit.")
                    }
                }.replaceAnnotatedString(
                    "adipiscing",
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Color.Blue)) {
                            append("adipiscing")
                        }
                    }
                )
            )
        }
    )
}