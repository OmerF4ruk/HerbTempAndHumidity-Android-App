package com.example.herbtempandhum

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.herbtempandhum.data.LoginRequest
import com.example.herbtempandhum.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.IOException

@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current
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
            onClick = {
                login(
                    email = userEmail,
                    password = userPassword,
                    navController = navController,
                    context
                )


            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100))
        ) {
            Text(text = "Giriş Yap", Modifier.padding(vertical = 8.dp))

        }


    }


}

fun login(email: String, password: String, navController: NavController, context: Context) {

    val loginRequest = LoginRequest();

    println("e: " + email)
    println("p: " + password)
    loginRequest.email = email;
    loginRequest.password = password
    val query: Call<User> = Retrofit.userApi.login(loginRequest)
    println("p: 1")
    var code = 1
    query.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            println("kod: " + response.code())
            if (response.code() == 201) {
                val user: User? = response.body()
                println((user?.id.toString()))
                navController.navigate(route = Screen.DeviceList.route + "/${user?.id.toString()}")
                Toast.makeText(/* context = */ context,/* text = */
                    "Giriş Başarılı",/* duration = */
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(/* context = */ context,/* text = */
                    "Hatalı Giriş Bilgileri",/* duration = */
                    Toast.LENGTH_SHORT).show()
            }


        }


        override fun onFailure(call: Call<User>, t: Throwable) {
            println("hata: " + t)
        }

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