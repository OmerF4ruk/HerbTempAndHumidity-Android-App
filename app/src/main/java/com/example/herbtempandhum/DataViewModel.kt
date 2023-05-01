package com.example.herbtempandhum

import androidx.lifecycle.ViewModel
import com.example.herbtempandhum.data.Data
import com.example.herbtempandhum.data.Retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    data class UiState(
        val isLoading: Boolean = false,
        val datas: List<Data> = emptyList(),
        val error: String = ""
    )

    init {
        getCoins(132123)
    }

    private fun getCoins(device_id:Int) {
        val query = Retrofit.dataApi.getData(device_id)
        query.enqueue(object : Callback<List<Data>> {
            override fun onResponse(
                call: Call<List<Data>>,
                response: Response<List<Data>>
            ) {println("aaaaaa"+response)

                val elements = response.body()
                println("aaaa"+elements)

                _state.update { it.copy(datas = elements ?: emptyList()) }

            }

            override fun onFailure(call: retrofit2.Call<List<Data>>, t: Throwable) {
                println(t)
            }
        })

    }
}