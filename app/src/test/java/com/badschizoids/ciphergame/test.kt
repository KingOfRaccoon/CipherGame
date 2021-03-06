package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.CaesarCipher

fun main(){
    val caesarCipher = CaesarCipher()
    val str = "Клубничная котейка"
    println(caesarCipher.encrypt(str))
    println(caesarCipher.decrypt(caesarCipher.encrypt(str)))
}
