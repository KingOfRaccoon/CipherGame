package com.badschizoids.ciphergame.ciphers

import kotlin.experimental.xor

class ByteCipher(private val key: Char = 'e'): EncryptAndDecrypt {

    override fun encrypt(string: String): String {
        val xorByte = key.toByte()
        val byteArray = string.toByteArray()
        val a = ByteArray(byteArray.size){byteArray[it].xor(xorByte)}

        return String(a)
    }

    override fun decrypt(string: String): String {
        val byteArray = string.toByteArray()
        val a = ByteArray(byteArray.size){byteArray[it].xor(key.toByte())}
        return String(a)
    }

}