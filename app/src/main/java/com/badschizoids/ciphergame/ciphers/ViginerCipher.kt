package com.badschizoids.ciphergame.ciphers

class ViginerCipher(var key : String = "Котлин"): EncryptAndDecrypt {

    override fun encrypt(string: String): String {
        val firstStep = 'а'.toInt()
        val output = StringBuffer()
        var previousAscii : Int
        var newAscii : Int
        for (i in string.indices){
            val char = string[i]
            if (char == ' '){
                output.append(char.toString())
                continue
            }
            previousAscii = char.toInt()
            val step = key[i % key.length].toLowerCase().toInt() - firstStep
            newAscii = previousAscii + step
            if (newAscii < 223 && Character.isUpperCase(char) || newAscii < 255)
                newAscii -= 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }

    override fun decrypt(string: String): String {
        val firstStep = 'а'.toInt()
        val output = StringBuffer()
        var previousAscii: Int
        var newAscii: Int
        for (i in string.indices) {
            val char = string[i]
            if (char == ' ') {
                output.append(char.toString())
                continue
            }
            previousAscii = char.toInt()
            val step = key[i % key.length].toLowerCase().toInt() - firstStep
            newAscii = previousAscii - step
            if (newAscii < 192 && Character.isUpperCase(char) || newAscii < 224)
                newAscii += 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }
}