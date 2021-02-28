package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher

fun main(){
    val string = "WELCOME TO THE"
    val str = "Просто здравствуй просто как дела"
    val generateEncryptMessage = GenerateEncryptMessage()
    val encryptMessage = generateEncryptMessage.generateMessage(string, 1)
    val caeserCipher = CaeserCipher()
    val viginerCipher = ViginerCipher()
    println(caeserCipher.encrypt(string))
    println(caeserCipher.encrypt(str))
    println(caeserCipher.decrypt(caeserCipher.encrypt(string)))
    println(caeserCipher.decrypt(caeserCipher.encrypt(str)))
    println()
    println(viginerCipher.encrypt(string))
    println(viginerCipher.encrypt(str))
    println(viginerCipher.decrypt(viginerCipher.encrypt(string)))
    println(viginerCipher.decrypt(viginerCipher.encrypt(str)))
    println()
    println(caeserCipher.decrypt(encryptMessage))
    println(viginerCipher.decrypt(encryptMessage))
    println(encryptMessage.reversed())
}