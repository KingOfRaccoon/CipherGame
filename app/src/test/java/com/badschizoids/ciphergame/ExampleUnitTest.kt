package com.badschizoids.ciphergame

import com.badschizoids.ciphergame.ciphers.ByteCipher
import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.SpinnerCipher
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
        val a_ceaser = "Денег нет, но вы держитесь"
        val enc = ceaser.encrypt(a_ceaser)
        val dec = ceaser.decrypt(enc)
        assertEquals(a_ceaser, dec)
    }

    @Test
    fun test_byte(){
        val byte = ByteCipher()
        val a_byte = "QWERTY"
        val enc = byte.encrypt(a_byte)
        val dec = byte.decrypt(enc)
        println(enc)
        println(dec)
        assertEquals(a_byte, dec)
    }

    @Test
    fun test_viginer(){
        val viginer = ViginerCipher()
        val a_viginer = "Пол — это лава"
        val enc = viginer.encrypt(a_viginer)
        val dec = viginer.decrypt(enc)
        assertEquals(a_viginer, dec)
    }

    @Test
    fun test_spinner() {
        val spinner = SpinnerCipher();
        var text = "кусок фигни"
        var enc = spinner.encrypt(text)
        var dec = spinner.decrypt(enc)
        assertEquals(text, dec)
    }
}
