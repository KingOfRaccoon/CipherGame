package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.ReverseCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher

class GenerateEncryptMessage {
    fun generateMessage(string: String, level: Int): String {
        var message = string
        val ciphers = mutableListOf(CaeserCipher(), ViginerCipher(), ReverseCipher())
        var count = 0
        when(level){
            in (0..2) ->{
                count = 1
            }
            in (3..5) ->{
                count = 2
            }
        }
        for (i in 0 until count){
            message = ciphers.random().encrypt(message)
        }
        return message
    }
}