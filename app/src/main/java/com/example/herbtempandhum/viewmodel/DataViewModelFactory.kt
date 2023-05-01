package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataViewModelFactory(private val device_id:String):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DataViewModel(device_id) as T
}