package com.example.herbtempandhum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DeviceListPage(
    navController: NavController,
    viewModel: DeviceListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var deviceId = "10001"

    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state.devices) { device ->
            DeviceBox(deviceId = device.airHumidity.toString(), navController)
        }
    }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Hoş Geldin", fontSize = 30.sp)
            Text(text = "Ömer", fontSize = 30.sp)
        }
        DeviceBox(deviceId = "10001",navController)
        DeviceBox(deviceId = "10002",navController)
        DeviceBox(deviceId = "10003",navController)


    }

}

@Composable
private fun DeviceBox(
    deviceId: String,navController: NavController
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { navController.navigate(route = Screen.DevicePage.route + "/$deviceId") })
            .background(Color.Gray)
            .fillMaxWidth()
            .size(100.dp)
            .height(150.dp)
    ) {
        Column(
            Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 10.dp)
                    .height(25.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(text = "CİHAZ İD:", fontSize = 20.sp)

                Text(text = deviceId, fontSize = 20.sp)
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun DeviceListPagePreview() {
    DeviceListPage(navController = rememberNavController())

}