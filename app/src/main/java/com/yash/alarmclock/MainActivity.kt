package com.yash.alarmclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.yash.alarmclock.ui.theme.AlarmClockTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlarmClockApp()
                }
            }
        }
    }
}

@Composable
fun AlarmClockApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Set Alarm",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TimePicker()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle alarm setting */ }) {
            Text(text = "Set Alarm")
        }
    }
}

@Composable
fun TimePicker() {
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberPicker(
            values = (0..23).toList(),
            onValueChange = { selectedHour = it },
            modifier = Modifier.weight(1f)
        )
        Text(text = ":", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp))
        NumberPicker(
            values = (0..59).toList(),
            onValueChange = { selectedMinute = it },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun NumberPicker(
    values: List<Int>,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        values.forEachIndexed { index, value ->
            Button(
                onClick = {
                    selectedIndex = index
                    onValueChange(value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (index == selectedIndex) Color.Gray else Color.White
                )
            ) {
                Text(text = value.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlarmClockApp() {
    AlarmClockApp()
}
