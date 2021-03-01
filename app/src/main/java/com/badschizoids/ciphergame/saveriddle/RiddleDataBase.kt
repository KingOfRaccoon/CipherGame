package com.badschizoids.ciphergame.saveriddle

import androidx.room.Database
import androidx.room.RoomDatabase
import com.badschizoids.ciphergame.tools.SolvedRiddle

@Database(entities = [SolvedRiddle::class], version = 1)
abstract class RiddleDataBase : RoomDatabase(){
    abstract fun solvedRiddleDao(): SolvedRiddleDao
}