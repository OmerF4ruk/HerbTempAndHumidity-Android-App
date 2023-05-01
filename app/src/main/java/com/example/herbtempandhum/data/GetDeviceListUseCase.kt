package com.example.herbtempandhum.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDeviceListUseCase {

    operator fun invoke(): Flow<Resource<List<Device>>> = flow {
        try {
            emit(Resource.Loading())
            //val coin = Retrofit.api.getDevices()
            //emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexcepted error occured."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}