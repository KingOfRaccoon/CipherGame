package com.badschizoids.ciphergame.ciphers;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AltByteCipher implements EncryptAndDecrypt {

    private char ch;

    public AltByteCipher() {
        this.ch = 'e';
    }

    public AltByteCipher(char ch) {
        this.ch = ch;
    }

    @NotNull
    @Override
    public String encrypt(@NotNull String string) {
        char[] chars = string.toCharArray();
        for(int i = 0; i != string.length(); ++i) {
            chars[i] = (char)(chars[i] ^ ch);
        }
        return new String(chars);
    }

    @NotNull
    @Override
    public String decrypt(@NotNull String string) {
        char[] chars = string.toCharArray();
        for(int i = 0; i != string.length(); ++i) {
            chars[i] = (char) (chars[i] ^ ch);
        }
        return new String(chars);
    }
}
