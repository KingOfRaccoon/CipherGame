package com.badschizoids.ciphergame.tools

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.badschizoids.ciphergame.saveriddle.RiddleDataBase

class CipherApplication: Application() {
    private lateinit var riddleDataBase: RiddleDataBase
    lateinit var instance : CipherApplication
    override fun onCreate() {
        super.onCreate()
        instance = this
        riddleDataBase = Room.databaseBuilder(
            this,
            RiddleDataBase::class.java,
            "database"
        )
            .build()
    }
    fun getDatabase() = riddleDataBase

    fun getInstanceApp() = instance
}