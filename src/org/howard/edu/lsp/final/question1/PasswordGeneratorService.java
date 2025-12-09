package org.howard.edu.lsp.finale.question1;

import java.util.Locale;

/**
 * Part C - Design Pattern Documentation
 *
 * Singleton:
 *  The service has one shared access point. getInstance() returns the single
 *  object used throughout the application.
 *
 * Strategy:
 *  Each password algorithm implements PasswordAlgorithm. The service can swap
 *  algorithms at runtime and new ones can be added without changing client code.
 *
 * These patterns meet the requirements for runtime selection, swappable behavior,
 * future expansion, and a single shared service instance.
 */

public final class PasswordGeneratorService {
    private static final PasswordGeneratorService INSTANCE = new PasswordGeneratorService();
    private PasswordAlgorithm algorithm; // selected strategy

    private PasswordGeneratorService() { }

    /**
     * Returns the single shared service instance.
     * @return singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }

    /**
     * Selects the algorithm strategy by name: "basic", "enhanced", or "letters".
     * Future algorithms can be added here without changing client code.
     * @param name algorithm name
     * @throws IllegalArgumentException if name is unknown
     */
    public void setAlgorithm(String name) {
        String key = name == null ? "" : name.toLowerCase(Locale.ROOT).trim();
        switch (key) {
            case "basic":    algorithm = new BasicAlgorithm();    break;
            case "enhanced": algorithm = new EnhancedAlgorithm(); break;
            case "letters":  algorithm = new LettersAlgorithm();  break;
            default: throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }

    /**
     * Generates a password of the given length using the selected algorithm.
     * @param length desired length
     * @return generated password
     * @throws IllegalStateException if no algorithm has been selected
     */
    public String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException("Algorithm not selected.");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length must be >= 0");
        }
        return algorithm.generate(length);
    }

    // Package-private helper to keep tests independent (not part of public API).
    void _resetForTests() { algorithm = null; }
}
