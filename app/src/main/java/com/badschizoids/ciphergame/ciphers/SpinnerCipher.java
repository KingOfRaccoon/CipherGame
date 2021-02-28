package com.badschizoids.ciphergame.ciphers;

import org.jetbrains.annotations.NotNull;

public class SpinnerCipher implements EncryptAndDecrypt {

    @NotNull
    private String spin(@NotNull String string) {
        int len = string.length();
        if (len <= 1) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len - len % 2 - 1; i += 2) {
            stringBuilder.append(string.charAt(i + 1));
            stringBuilder.append(string.charAt(i));
        }

        if (len % 2 == 1) {
            stringBuilder.append(string.charAt(len - 1));
        }

        return stringBuilder.toString();
    }

    @NotNull
    @Override
    public String encrypt(@NotNull String string) {
        return spin(string);
    }

    @NotNull
    @Override
    public String decrypt(@NotNull String string) {
        return spin(string);
    }
}
