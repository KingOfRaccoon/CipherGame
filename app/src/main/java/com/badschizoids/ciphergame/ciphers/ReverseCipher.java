package com.badschizoids.ciphergame.ciphers;

import org.jetbrains.annotations.NotNull;

public class ReverseCipher implements EncryptAndDecrypt {

    @NotNull
    @Override
    public String encrypt(@NotNull String string) {
        return new StringBuffer(string).reverse().toString();
    }

    @NotNull
    @Override
    public String decrypt(@NotNull String string) {
        return new StringBuffer(string).reverse().toString();
    }
}