package com.example.herbtempandhum

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DevicePage(id: String?) {
    println("deneme")
    Column() {
        Text(text = id.toString())
    }
}



