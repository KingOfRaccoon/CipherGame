package com.badschizoids.ciphergame.ciphers

interface EncryptAndDecrypt {
    fun encrypt(string: String): String

    fun decrypt(string: String): String
}