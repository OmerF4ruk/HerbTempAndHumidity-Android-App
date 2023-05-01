package com.example.herbtempandhum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable

fun DevicePage(id: String?,viewModel: DataViewModel = viewModel()) {
    println("deneme")
    Column() {

    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(color = Color.White)
    ) {

    }
    val state by viewModel.state.collectAsState()
    println(state.datas)

    LazyColumn(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(state.datas) { data ->
            println(data)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun DevicePagePreview() {
    DevicePage(id = "1")
}


