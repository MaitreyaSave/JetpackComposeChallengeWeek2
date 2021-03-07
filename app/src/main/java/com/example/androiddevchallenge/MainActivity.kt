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
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.Timer
import java.util.TimerTask

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
    val countDownFinished = remember { mutableStateOf(false) }
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading
            Text(
                text = "Countdown Timer",
                style = TextStyle(fontSize = 32.sp),
                modifier = Modifier.padding(10.dp)
            )

            TimerDetails(countDownFinished)
            if (countDownFinished.value) {
                Text(
                    text = "Countdown Finished!!!",
                    style = TextStyle(fontSize = 32.sp, color = Color.Red),
                    modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
fun TimerDetails(countDownFinished: MutableState<Boolean>) {
    val firstTimeRun = remember { mutableStateOf(true) }
    val countDownSeconds = remember { mutableStateOf(0) }
    val isCountingDown = remember { mutableStateOf(false) }
    var timer: Timer? = null
    val timerTask = object : TimerTask() {
        override fun run() {
            if (isCountingDown.value) {
                countDownSeconds.value--
                if (countDownSeconds.value <= 0) {
                    countDownFinished.value = true
                    isCountingDown.value = false
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Pause/Play/Reset buttons
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 50.dp, 0.dp, 70.dp)
        ) {
            Button(
                enabled = !isCountingDown.value,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(100.dp),
                onClick = {
                    if (firstTimeRun.value) {
                        timer = Timer()
                        timer?.schedule(timerTask, 0, 1000)
                        firstTimeRun.value = false
                    }
                    if (countDownSeconds.value > 0) {
                        isCountingDown.value = true
                        countDownFinished.value = false
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    Modifier
                        .size(60.dp)
                )
            }
            Button(
                enabled = isCountingDown.value,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(100.dp),
                onClick = {
                    isCountingDown.value = false
                    timer?.cancel()
                    timer?.purge()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Pause,
                    contentDescription = "Pause",
                    Modifier
                        .size(60.dp)
                )
            }
            Button(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(100.dp),
                onClick = {
                    isCountingDown.value = false
                    countDownSeconds.value = 0
                    timer?.cancel()
                    timer?.purge()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Reset",
                    Modifier
                        .size(60.dp)
                )
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
                text = "Hrs",
                style = TextStyle(fontSize = 24.sp)
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Min",
                style = TextStyle(fontSize = 24.sp)
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Sec",
                style = TextStyle(fontSize = 24.sp)
            )
        }

        // Button Row to increase values
        ButtonRow("+", countDownSeconds, 1)

        // Values
        val valueHrs: Int = (countDownSeconds.value / 3600) % 60
        val valueMin: Int = (countDownSeconds.value / 60) % 60
        val valueSec: Int = countDownSeconds.value % 60
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
                text = valueHrs.toString().padStart(2, '0'),
                style = TextStyle(fontSize = 48.sp)
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = valueMin.toString().padStart(2, '0'),
                style = TextStyle(fontSize = 48.sp)
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = valueSec.toString().padStart(2, '0'),
                style = TextStyle(fontSize = 48.sp)
            )
        }

        // Button Row to decrease values
        ButtonRow("-", countDownSeconds, -1)
    }
}

@Composable
fun ButtonRow(buttonText: String, countDownSeconds: MutableState<Int>, sign: Int) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = {
                countDownSeconds.value += sign * 3600
                if (countDownSeconds.value < 0) {
                    countDownSeconds.value -= sign * 3600
                    Toast.makeText(context, "Value cannot be less than 0", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = buttonText)
        }
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = {
                countDownSeconds.value += sign * 60
                if (countDownSeconds.value < 0) {
                    countDownSeconds.value -= sign * 60
                    Toast.makeText(context, "Value cannot be less than 0", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = buttonText)
        }
        Button(
            modifier = Modifier
                .weight(1F)
                .padding(4.dp),
            onClick = {
                countDownSeconds.value += sign
                if (countDownSeconds.value < 0) {
                    countDownSeconds.value -= sign
                    Toast.makeText(context, "Value cannot be less than 0", Toast.LENGTH_SHORT).show()
                }
            }
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
