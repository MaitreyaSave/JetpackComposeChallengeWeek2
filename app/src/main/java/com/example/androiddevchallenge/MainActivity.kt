/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading
            Text(
                text = "Countdown Timer",
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier.padding(10.dp)
            )
            TimerDetails()
        }

    }
}

@Composable
fun TimerDetails() {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Pause/Play/Reset buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1F)
                    .size(50.dp)
                    .padding(4.dp),
                onClick = { /*TODO*/ }
            ) {
//                Text(text = ">")
                // Check how to make button square
            }
            Button(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "||")
            }
            Button(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "?")
            }
        }


        // Labels
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Hrs"
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Min"
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Sec"
            )
        }
        ButtonRow("+")
        Row() {
            Text(text = "00")
        }
        ButtonRow("-")
    }
}

@Composable
fun ButtonRow(buttonText:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = buttonText)
        }
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = buttonText)
        }
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
