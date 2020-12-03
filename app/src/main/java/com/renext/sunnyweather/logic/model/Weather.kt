package com.renext.sunnyweather.logic.model

data class Weather(
    val realTime: RealtimeResponse.Realtime,
    val daily: DailyResponse.Daily
)