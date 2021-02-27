package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.ByteCipher
import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
     @Test
    fun test_ceaser() {
        val ceaser = CaeserCipher()
        var a_ceaser = "Денег нет, но вы держитесь"
        var enc = ceaser.encrypt(a_ceaser)
        var dec = ceaser.decrypt(enc)
        assertEquals(a_ceaser, dec)
    }

    @Test
    fun test_byte(){
        val byte = ByteCipher()
        var a_byte = "QWERTY"
        var enc = byte.encrypt(a_byte)
        var dec = byte.decrypt(enc)
        println(enc)
        println(dec)
        assertEquals(a_byte, dec)
    }

    @Test
    fun test_viginer(){
        val viginer = ViginerCipher()
        var a_viginer = "Пол — это лава"
        var enc = viginer.encrypt(a_viginer)
        var dec = viginer.decrypt(enc)
        assertEquals(a_viginer, dec)
    }
}
