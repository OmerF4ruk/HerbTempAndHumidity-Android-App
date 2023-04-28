package com.example.herbtempandhum

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import okhttp3.*
import java.io.IOException

@Composable
fun LoginPage(navController: NavController) {
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var userEmail: String by remember { mutableStateOf("") }
        TextField(
            value = userEmail,
            label = { Text(text = "Email") },
            onValueChange = { userEmail = it })

        var userPassword: String by remember { mutableStateOf("") }
        TextField(
            value = userPassword,
            label = { Text(text = "Password") },
            onValueChange = { userPassword = it })

        Button(
            onClick = { navController.navigate(route = Screen.DeviceList.route) },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(100))
        ) {
            Text(text = "Giriş Yap", Modifier.padding(vertical = 8.dp))

        }


    }


}

fun login() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("url")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
    })
}

@Composable
private fun TextInput(
    type: String
) {
    var value: String by remember { mutableStateOf("") }
    TextField(value = value, label = { Text(text = type) }, onValueChange = { value = it })

}

@Composable
@Preview(showBackground = true)
fun LoginPagePreview() {
    LoginPage(navController = rememberNavController())

}