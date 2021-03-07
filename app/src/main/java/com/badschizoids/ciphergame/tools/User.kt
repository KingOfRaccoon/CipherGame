package com.badschizoids.ciphergame.tools

import android.app.AlertDialog
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.badschizoids.ciphergame.Mem

object User {
    private val list = listOf<Mem>()
    val mutableLiveData = MutableLiveData(list)

    val helps = mutableListOf<AlertDialog>()

    val setHelpMessages = MutableLiveData(false)

    fun buildSoundPool(maxStreams: Int): SoundPool {
        val attrs = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        return SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(maxStreams)
                .build()
    }

    val haveThisMemes = mutableListOf<Mem>()
    val haveThisMemesThird = mutableListOf<String>()

    var chat = Chat("", arrayOf(), arrayOf())
    var messages = mutableListOf<Message>()
    var notNewFrame = false
    var nameCompany = ""

    var keyCaesar = 3
    var keyViginer = "Котлин"
    val mutableLiveDataKeyCaesar = MutableLiveData(keyCaesar)
    val mutableLiveDataKeyViginer = MutableLiveData(keyViginer)
}