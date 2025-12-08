package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Digits-only generator using java.util.Random.
 */
class BasicAlgorithm implements PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private final Random r = new Random();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(DIGITS.charAt(r.nextInt(DIGITS.length())));
        }
        return sb.toString();
    }
}
