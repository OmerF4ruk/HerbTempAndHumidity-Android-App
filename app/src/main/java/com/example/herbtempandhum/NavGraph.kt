package com.example.herbtempandhum

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
            route = Screen.DeviceList.route
        ) {
            DeviceListPage(navController)
        }
        composable(
            route=Screen.DevicePage.route + "/{Device_id}"
        ) { navBackStack ->
            val id = navBackStack.arguments?.getString("Device_id")
            DevicePage(id)
        }

    }
}