package com.example.herbtempandhum

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.herbtempandhum.pages.AddDevicePage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginPage(navController)
        }
        composable(
            route = Screen.DeviceList.route+ "/{user_id}"
        ) {navBackStack ->
            val id = navBackStack.arguments?.getString("user_id")
            DeviceListPage(id,navController)
        }
        composable(
            route=Screen.DevicePage.route + "/{Device_id}"
        ) { navBackStack ->
            val id = navBackStack.arguments?.getString("Device_id")
            DevicePage(id)
        }
        composable(
            route=Screen.AddDevicePage.route + "/{Device_id}"
        ) { navBackStack ->
            val id = navBackStack.arguments?.getString("Device_id")
            AddDevicePage(id,navController)
        }

    }
}