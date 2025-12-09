package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/** Digits-only generator using java.util.Random. */
class BasicAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String digits = "0123456789";
        Random r = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(digits.length());
            result += digits.charAt(index);   // naive concat
        }
        return result;
    }
}
