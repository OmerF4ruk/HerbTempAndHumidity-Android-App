package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import com.example.herbtempandhum.Retrofit
import com.example.herbtempandhum.data.Machine.PredData
import com.example.herbtempandhum.data.Machine.ResultPredict
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MachineViewModel(device_id: String ):ViewModel() {

    private val _state = MutableStateFlow(DataViewModel.UiState())
    val state: StateFlow<DataViewModel.UiState> = _state

    data class UiState(
        val isLoading: Boolean = false,
        var data: String = "",
        val error: String = "",
    )

    init {
        println("id: " + state.value.predData)
        getCoins(device_id)
    }

     fun getCoins(device_id:String)  {
        println(11)
        val query = Retrofit.machineApi.Predict(device_id)
        println(22)
        query.enqueue(object : Callback<ResultPredict> {
            override fun onResponse(call: Call<ResultPredict>, response: Response<ResultPredict>) {
                println("pred response "+response)
                val pred = response.body()!!
                println("pred: "+ pred)
                _state.update { it.copy(predict = pred.predict.toString() )}

            }

            override fun onFailure(call: Call<ResultPredict>, t: Throwable) {
                print("TTTT: "+t)
            }

        })

    }
}
