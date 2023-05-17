package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DeviceListViewModelFactory(private val device_id: String?): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DeviceListViewModel(device_id!!) as T
}