package com.renext.sunnyweather.logic

import androidx.lifecycle.liveData
import com.renext.sunnyweather.logic.model.Place
import com.renext.sunnyweather.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


//    fun refreshWeather(lng: String, lat: String, placeName: String) = fire(Dispatchers.IO) {
//        coroutineScope {
//            val deferredRealtime = async {
//                SunnyWeatherNetWork.getRealtimeWeather(lng, lat)
//            }
//            val deferredDaily = async{
//                SunnyWeatherNetWork.getDailyWeather(lng,lat)
//            }
//
//            val realtimeResponse = deferredRealtime.await()
//            val dailyRespone = deferredDaily.await()
//            if (realtimeResponse.status == "ok" && dailyRespone.status = "ok"){
//                val weather = Weather(realtimeResponse.result)
//            }
//        }
//    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }



}