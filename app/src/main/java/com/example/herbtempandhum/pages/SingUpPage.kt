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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.herbtempandhum.data.LoginRequest
import com.example.herbtempandhum.data.RegisterRequest
import com.example.herbtempandhum.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.IOException

@Composable
fun SingUpPage(navController: NavController) {
    val context = LocalContext.current
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var name: String by remember { mutableStateOf("") }
        TextField(
            value = name,
            label = { Text(text = "İsim") },
            onValueChange = { name = it })
        var userEmail: String by remember { mutableStateOf("") }
        TextField(
            value = userEmail,
            label = { Text(text = "E-mail") },
            onValueChange = { userEmail = it })

        var userPassword: String by remember { mutableStateOf("") }
        TextField(
            value = userPassword,
            label = { Text(text = "Şifre") },
            onValueChange = { userPassword = it })

        Button(
            onClick = {
                signUp(
                    email = userEmail,
                    password = userPassword,
                    name = name,
                    navController = navController,
                    context
                )


            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100))
        ) {
            Text(text = "Kayıt Ol", Modifier.padding(vertical = 5.dp),style = TextStyle(
                fontSize = 19.sp)
            )

        }


    }


}

fun signUp(
    email: String,
    password: String,
    name: String,
    navController: NavController,
    context: Context
) {

    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(/* context = */ context,/* text = */
            "Lütfen Boşlukları Doldurunuz",/* duration = */
            Toast.LENGTH_SHORT
        ).show()
    } else {
        val e=email.replace(" ", "")
        val p=email.replace(" ", "")
        val n=email.replace(" ", "")
        val registerRequest = RegisterRequest(e,p,n)

        val query: Call<User> = Retrofit.userApi.signUp(registerRequest)
        var code = 1
        query.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                println("kod: " + response.code())
                if (response.code() == 201) {
                    println("user: "+response)
                    val user: User? = response.body()
                    println((user?.id.toString()))
                    navController.navigate(route = Screen.Login.route)
                    Toast.makeText(/* context = */ context,/* text = */
                        "Kayıt Başarrılı",/* duration = */
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(/* context = */ context,/* text = */
                        "Bu E-mail Daha Kullanılıyor Olabilir!",/* duration = */
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }


            override fun onFailure(call: Call<User>, t: Throwable) {
                println("hata: " + t)
            }

        })

    }


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
fun SingUpPage() {
    LoginPage(navController = rememberNavController())

}