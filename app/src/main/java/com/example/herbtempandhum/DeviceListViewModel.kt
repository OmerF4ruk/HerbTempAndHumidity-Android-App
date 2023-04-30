package com.example.herbtempandhum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.data.GetDeviceListUseCase
import com.example.herbtempandhum.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DeviceListViewModel(
    private val getDeviceListUseCase: GetDeviceListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    data class UiState(
        val isLoading: Boolean = false,
        val devices: List<Device> = emptyList(),
        val error: String = ""
    )

    init {
        getCoins()
    }

    private fun getCoins() {
        getDeviceListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UiState(devices = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UiState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}