package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/** A–Z, a–z, 0–9 using SecureRandom. */
class EnhancedAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                       + "abcdefghijklmnopqrstuvwxyz"
                       + "0123456789";
        SecureRandom r = new SecureRandom();
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(allowed.length());
            result += allowed.charAt(index);   // naive concat
        }
        return result;
    }
}
