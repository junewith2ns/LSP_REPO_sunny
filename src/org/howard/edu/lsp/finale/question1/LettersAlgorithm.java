package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/** Letters-only generator (A–Z, a–z). */
class LettersAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom r = new SecureRandom();
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(letters.length());
            result += letters.charAt(index);   // naive concat
        }
        return result;
    }
}
