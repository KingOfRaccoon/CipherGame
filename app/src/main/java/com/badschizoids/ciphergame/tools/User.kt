package com.badschizoids.ciphergame.tools

import androidx.lifecycle.MutableLiveData
import com.badschizoids.ciphergame.Mem

object User {
    private val list = listOf<Mem>()
    val mutableLiveData = MutableLiveData(list)
}