package com.example.herbtempandhum.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.herbtempandhum.Retrofit
import com.example.herbtempandhum.Screen
import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.data.LoginRequest
import com.example.herbtempandhum.data.NewDevice
import com.example.herbtempandhum.data.User
import com.example.herbtempandhum.login
import com.example.herbtempandhum.viewmodel.DeviceListViewModel
import com.example.herbtempandhum.viewmodel.DeviceListViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AddDevicePage(
    user_id: String?,
    navController: NavController,
    //     viewModel: DeviceListViewModel = viewModel(factory = DeviceListViewModelFactory(user_id))
) {
    val context = LocalContext.current
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var deviceName: String by remember { mutableStateOf("") }
        TextField(
            value = deviceName,
            label = { Text(text = "Cihaz Adı") },
            onValueChange = { deviceName = it })

        var deviceId: String by remember { mutableStateOf("") }
        TextField(
            value = deviceId,
            label = { Text(text = "Cihaz ID") },
            onValueChange = { deviceId = it })

        Button(
            onClick = {
                addDevice(user_id!!, deviceId, deviceName, navController, context)


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(100)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D5260))
        ) {
            Text(text = "Cihaz Ekle", Modifier.padding(vertical = 8.dp))

        }


    }


}

fun addDevice(
    user_id: String,
    deviceId: String,
    deviceName: String,
    navController: NavController,
    context: Context
) {
    if(deviceId.isEmpty()||user_id.isEmpty()){
        Toast.makeText(/* context = */ context,/* text = */
            "Lütfen Boşlukları Doldurunuz!",/* duration = */
            Toast.LENGTH_SHORT
        ).show()
    }else {
        val device = NewDevice( deviceId.replace(" ", ""), user_id, deviceName.replace(" ", ""));
        println(device)
        println("user_id: " + user_id)
        val query: Call<Device> = Retrofit.deviceApi.addDevice(device)
        println("p: 1")
        var code = 1
        query.enqueue(object : Callback<Device> {
            override fun onResponse(call: Call<Device>, response: Response<Device>) {
                println("kod: " + response)
                if (response.code() == 201) {
                    val device: Device? = response.body()
                    println((device?.id.toString()))
                    navController.navigate(route = Screen.DeviceList.route + "/${user_id}")
                    Toast.makeText(/* context = */ context,/* text = */
                        "Cihaz Başarıyla Eklendi",/* duration = */
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 400) {

                    Toast.makeText(/* context = */ context,/* text = */
                        "Cihaz Daha Önce Kaydedilmiş!",/* duration = */
                        Toast.LENGTH_SHORT
                    ).show()

                }else{
                    Toast.makeText(/* context = */ context,/* text = */
                        "Cihaz Eklenirken Hata Meydana Geldi!",/* duration = */
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            override fun onFailure(call: Call<Device>, t: Throwable) {
                println("hata: " + t)
            }

        })

    }



}