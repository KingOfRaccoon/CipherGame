package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher

fun main(){
    val string = "Привет как дела"
    val generateEncryptMessage = GenerateEncryptMessage()
    val encryptMessage = generateEncryptMessage.generateMessage(string, 4)
    val caeserCipher = CaeserCipher()
    val viginerCipher = ViginerCipher()
    println(encryptMessage)
    println(caeserCipher.decrypt(encryptMessage))
    println(viginerCipher.decrypt(encryptMessage))
}