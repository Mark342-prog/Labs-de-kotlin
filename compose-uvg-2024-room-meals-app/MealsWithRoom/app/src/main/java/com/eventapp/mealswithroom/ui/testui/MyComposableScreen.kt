package com.eventapp.mealswithroom.ui.testui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MyComposableScreen() {
    var text by remember { mutableStateOf("Hello, World!") }

    Column {
        Text(text = text)
        Button(onClick = { text = "Hello, Compose!" }) {
            Text("Click Me")
        }
    }
}