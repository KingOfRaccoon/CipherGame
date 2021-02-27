package com.badschizoids.ciphergame.ciphers

class CaeserCipher(var key : Int = 3): EncryptAndDecrypt {

    override fun encrypt(string: String): String {
        val output = StringBuffer()
        var character : Char
        var previousAscii : Int
        var newAscii : Int
        for (i in string.indices){
            character = string[i]
            if (character == ' ') {
                output.append(character.toString())
                continue
            }
            previousAscii = character.toInt()
            newAscii = previousAscii + key
            if (newAscii < 223 && Character.isUpperCase(character) || newAscii < 255)
                newAscii -= 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }

    override fun decrypt(string: String): String {
        val output = StringBuffer()
        var character : Char
        var previousAscii : Int
        var newAscii : Int
        for (i in string.indices){
            character = string[i]
            if (character == ' ') {
                output.append(character.toString())
                continue
            }
            previousAscii = character.toInt()
            newAscii = previousAscii - key
            if (newAscii < 192 && Character.isUpperCase(character) || newAscii < 224)
                newAscii += 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }
}