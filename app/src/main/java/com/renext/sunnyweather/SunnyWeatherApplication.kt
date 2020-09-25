package com.renext.sunnyweather

import android.app.Application
import android.content.Context


class SunnyWeatherApplication : Application() {

    companion object {
        lateinit var context: Context
        const val TOKEN = "tDDFg4tnGKpD4RFk"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}