package com.example.android.myweatherapp.view

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android.myweatherapp.model.WeatherDTO

const val MAIN_SERVICE_STRING_EXTRA = "MainServiceExtra"
const val MAIN_SERVICE_INT_EXTRA = "MainServiceIntExtra"

var loader : WeatherLoader? = null

//class MainService (name: String = "MainService") : IntentService(name) {

//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun onHandleIntent(intent: Intent?) {
//        intent?.let {
//            loader = it.getParcelableExtra(MAIN_SERVICE_STRING_EXTRA)
//            loader?.loadWeather()
////            SendInfoAboutConnecting(it.getParcelableExtra<WeatherLoader>(MAIN_SERVICE_STRING_EXTRA) as WeatherLoader)
//        }
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
//    private fun SendInfoAboutConnecting(loader : WeatherLoader)  {
//        val broadcastIntent = Intent(CONNECT_IS_SUCCESS)
////        broadcastIntent.putExtra(THREADS_FRAGMENT_BROADCAST_EXTRA, infoAboutConnect)
//        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
//    }

//}