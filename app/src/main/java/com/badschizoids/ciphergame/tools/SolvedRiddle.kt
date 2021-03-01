package com.badschizoids.ciphergame.tools

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SolvedRiddle(
        val mem: String,
        val numberOfAttempts : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}