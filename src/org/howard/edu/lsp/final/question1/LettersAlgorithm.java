package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Letters-only generator (A–Z, a–z) using SecureRandom.
 */
class LettersAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom r = new SecureRandom();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += letters.charAt(r.nextInt(letters.length()));
        }
        return result;
    }
}

