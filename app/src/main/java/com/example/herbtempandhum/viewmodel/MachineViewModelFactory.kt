package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.herbtempandhum.data.Machine.PredData

class MachineViewModelFactory(private val device_id:String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MachineViewModel(device_id) as T
}