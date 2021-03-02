package com.badschizoids.ciphergame.ciphers

class ViginerCipher(var key : String = "Котлин"): EncryptAndDecrypt {

    override fun encrypt(string: String): String {
        val firstStep = 'а'.toInt()
        val output = StringBuffer()
        var previousAscii : Int
        var newAscii : Int
        val operators = ",.?!-"
        for (i in string.indices){
            val char = string[i]
            if (char == ' ' || char in operators){
                output.append(char.toString())
                continue
            }
            previousAscii = char.toInt()
            val step = key[i % key.length].toLowerCase().toInt() - firstStep
            newAscii = previousAscii + step
            when(previousAscii){
                in (65..122) -> {
                    if (newAscii > 90 && Character.isUpperCase(char) || newAscii > 122 && Character.isLowerCase(char)) {
                        newAscii -= 26
                    }
                }
                in (192..255) -> {
                    if (newAscii > 223 && Character.isUpperCase(char) || newAscii > 255 && Character.isLowerCase(char))
                        newAscii -= 32
                }
            }
//            if (newAscii < 223 && Character.isUpperCase(char) || newAscii < 255)
//                newAscii -= 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }

    override fun decrypt(string: String): String {
        val firstStep = 'а'.toInt()
        val output = StringBuffer()
        var previousAscii: Int
        var newAscii: Int
        val operators = ",.?!-"
        for (i in string.indices) {
            val char = string[i]
            if (char == ' ' || char in operators) {
                output.append(char.toString())
                continue
            }
            previousAscii = char.toInt()
            val step = key[i % key.length].toLowerCase().toInt() - firstStep
            newAscii = previousAscii - step
            when(previousAscii){
                in (65..122) -> {
                    if (newAscii < 65 && Character.isUpperCase(char) || newAscii < 97 && Character.isLowerCase(char)) {
                        newAscii += 26
                    }
                }
//                in (97..122) -> {
//                    if (newAscii > 65 && Character.isUpperCase(character) || newAscii > 97) {
//                        newAscii += 26
//                    }
//                }

                in (192..255) -> {
                    if (newAscii < 192 && Character.isUpperCase(char) || newAscii < 224 && Character.isLowerCase(char))
                        newAscii += 32
                }
            }
//            if (newAscii < 192 && Character.isUpperCase(char) || newAscii < 224)
//                newAscii += 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }
}