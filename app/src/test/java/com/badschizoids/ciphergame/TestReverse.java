package com.badschizoids.ciphergame;

import com.badschizoids.ciphergame.ciphers.AltByteCipher;
import com.badschizoids.ciphergame.ciphers.ByteCipher;
import com.badschizoids.ciphergame.ciphers.ReverseCipher;

import org.junit.Test;

public class TestReverse {
    @Test
    public void testReverse() {
        ReverseCipher rc = new ReverseCipher();
        String str = rc.encrypt("abcd");
        assert str != "abcd" : "wrong reverse encrypt " + str;
    }

    @Test
    public void charTest() {
        AltByteCipher cipher = new AltByteCipher('в');
        assert cipher.decrypt(cipher.encrypt("шифр")) == "шифр" : "byte works wrong";
    }
}
