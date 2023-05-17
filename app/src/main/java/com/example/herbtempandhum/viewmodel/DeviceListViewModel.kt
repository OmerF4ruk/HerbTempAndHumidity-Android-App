package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.Retrofit
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeviceListViewModel(
    user_id: String
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    data class UiState(
        val isLoading: Boolean = false,
        val devices: List<Device> = emptyList(),
        val error: String = ""
    )

    init {
        getCoins(user_id)
    }

    private fun getCoins(user_id: String) {
        val query = Retrofit.deviceApi.getDevices(1)
        query.enqueue(object : Callback<List<Device>> {
            override fun onResponse(
                call: Call<List<Device>>,
                response: Response<List<Device>>
            ) {
                val elements = response.body()

                _state.update { it.copy(devices = elements ?: emptyList()) }

            }

            override fun onFailure(call: retrofit2.Call<List<Device>>, t: Throwable) {
                println(t)
            }
        })

    }
}