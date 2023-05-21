package com.example.herbtempandhum

sealed class Screen(val route: String) {
    object Login : Screen(route = "login_page")
    object SignUp : Screen(route = "sign_up_page")
    object DeviceList : Screen(route = "device_list_page")
    object DevicePage : Screen(route = "device_page")
    object AddDevicePage : Screen(route = "add_device_page")
}