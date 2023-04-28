package com.example.herbtempandhum

sealed class Screen(val route: String) {
    object Login : Screen(route = "login_page")
    object DeviceList : Screen(route = "device_list_page")
    object DevicePage : Screen(route = "device_page")
}