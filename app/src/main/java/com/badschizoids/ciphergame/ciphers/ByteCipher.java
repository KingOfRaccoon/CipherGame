package com.badschizoids.ciphergame.ciphers;

import org.jetbrains.annotations.NotNull;

public class ByteCipher implements EncryptAndDecrypt {

    private char ch;

    public ByteCipher() {
        this.ch = 'e';
    }

    public ByteCipher(char ch) {
        this.ch = ch;
    }

    @NotNull
    private String xor(@NotNull String string) {
        char[] chars = string.toCharArray();
        for(int i = 0; i != string.length(); ++i) {
            chars[i] = (char)(chars[i] ^ ch);
        }
        return new String(chars);
    }

    @NotNull
    @Override
    public String encrypt(@NotNull String string) {
        return xor(string);
    }

    @NotNull
    @Override
    public String decrypt(@NotNull String string) {
        return xor(string);
    }
}
