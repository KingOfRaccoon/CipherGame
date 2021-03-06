package com.badschizoids.ciphergame.ciphers

class CaesarCipher(var key: Int = 3): EncryptAndDecrypt {

    override fun encrypt(string: String): String {
        val output = StringBuffer()
        var character : Char
        var previousAscii : Int
        var newAscii : Int
        val operators = ",.?!-"
        for (i in string.indices){
            character = string[i]
            if (character == ' ' || character in operators) {
                output.append(character.toString())
                continue
            }
            previousAscii = character.toInt()
            newAscii = previousAscii + key
            when(previousAscii){
                in (65..122) -> {
                    if (newAscii > 90 && Character.isUpperCase(character) || newAscii > 122) {
                        newAscii -= 26
                    }
                }
                in (1040..1103) -> { //1040-1071   1072-1103
                    if (newAscii > 1071 && Character.isUpperCase(character) || newAscii > 1103)
                        newAscii -= 32
                }
            }
//            if (newAscii < 223 && Character.isUpperCase(character) || newAscii < 255)
//                newAscii -= 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }

    override fun decrypt(string: String): String {
        val output = StringBuffer()
        var character : Char
        var previousAscii : Int
        var newAscii : Int
        val operators = ",.?!-"
        for (i in string.indices){
            character = string[i]
            if (character == ' ' || character in operators) {
                output.append(character.toString())
                continue
            }
            previousAscii = character.toInt()
            newAscii = previousAscii - key
            when(previousAscii){
                in (65..122) -> {
                    if (newAscii < 65 && Character.isUpperCase(character) || newAscii < 97 && Character.isLowerCase(character)) {
                        newAscii += 26
                    }
                }
//                in (97..122) -> {
//                    if (newAscii > 65 && Character.isUpperCase(character) || newAscii > 97) {
//                        newAscii += 26
//                    }
//                }

                in (1040..1103) -> { //1040-1071   1072-1103
                    if (newAscii < 1040 && Character.isUpperCase(character)
                            || newAscii < 1072 && Character.isLowerCase(character)) {
                        newAscii += 32
                    }
                }
            }
//            if (newAscii < 192 && Character.isUpperCase(character) || newAscii < 224)
//                newAscii += 32
            output.append(newAscii.toChar().toString())
        }
        return output.toString()
    }
}