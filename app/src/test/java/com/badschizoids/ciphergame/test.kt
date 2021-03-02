package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.CaesarCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher

fun main(){
    val string = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    val string1 = string.toLowerCase()

    val caesarCipher = CaesarCipher()
    val encString = caesarCipher.encrypt(string1)
    println(encString)

}