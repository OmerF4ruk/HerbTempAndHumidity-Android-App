package com.example.herbtempandhum.viewmodel

import androidx.lifecycle.ViewModel
import com.example.herbtempandhum.data.Data
import com.example.herbtempandhum.Retrofit
import com.example.herbtempandhum.data.Machine.PredData
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs

class DataViewModel(device_id: String ) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state


    data class UiState(
        val isLoading: Boolean = false,
        val datas: List<Data> = emptyList(),
        val predict:String="",
        val error: String = "",
        var predData:PredData= PredData(1,1,1,0)
    )


    init {

        println("id: "+device_id)
        getCoins(device_id)
        println("state pred "+state.value.predData)

    }

    private fun getCoins(device_id:String) {

        val query = Retrofit.dataApi.getData(device_id)
        query.enqueue(object : Callback<List<Data>> {
            override fun onResponse(
                call: Call<List<Data>>,
                response: Response<List<Data>>
            ) {
                println("aaaaaa"+response)
                println("response: "+response)
                val elements = response.body()
                _state.update { it.copy(datas = elements ?: emptyList()) }


                println("elements"+elements!![1])
                val i1:Int=(elements[0].soil_humidity).toInt()
                val i2:Int=(elements[1].soil_humidity).toInt()
                var sulanma:Int
                if(abs((i2 - i1)) >10)
                    sulanma=1
                else
                    sulanma=0
                _state.update { it.copy(predData = transformData(elements[0],sulanma)) }






            }

            override fun onFailure(call: retrofit2.Call<List<Data>>, t: Throwable) {
                println("tttt"+t)
            }
        })

    }

    fun transformData(data: Data,sulanma:Int):PredData{
    val predData:PredData= PredData((data.air_temperature.toFloat()).toInt(),data.air_humidity.toInt(),(data.soil_temperature.toFloat()).toInt(),sulanma)
    println("preddata"+predData)
    return predData

    }
}