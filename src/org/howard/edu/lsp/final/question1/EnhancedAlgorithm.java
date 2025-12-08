package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Alphanumeric generator (A–Z, a–z, 0–9) using SecureRandom.
 */
class EnhancedAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                         "abcdefghijklmnopqrstuvwxyz" +
                         "0123456789";
        SecureRandom r = new SecureRandom();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += allowed.charAt(r.nextInt(allowed.length()));
        }
        return result;
    }
}
