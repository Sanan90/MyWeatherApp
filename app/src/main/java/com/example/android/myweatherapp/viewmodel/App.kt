package com.example.android.myweatherapp.viewmodel

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.myweatherapp.room.HistoryDao
import com.example.android.myweatherapp.room.HistoryDataBase
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

//         by Lazy  - Это отложенная инициализация, которая будет вызвана только тогла когл
        private val history_dao by lazy {
            Room
                .databaseBuilder(
                    appInstance!!.applicationContext,
                    HistoryDataBase::class.java,
                    DB_NAME
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .historyDao()
        }

        fun getHistoryDao(): HistoryDao = history_dao
//            if (db == null) {
//                synchronized(HistoryDao::class.java) {
//                    if (db == null) {
//                        if (appInstance == null) throw IllegalStateException("ERROR")
//                        db = Room.databaseBuilder(
//                            appInstance!!.applicationContext,
//                            HistoryDataBase::class.java,
//                            DB_NAME
//                        ).allowMainThreadQueries()
//                            .build()
//                    }
//                }
//            }
//            return db!!.historyDao()

    }

}