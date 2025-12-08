/**
 * Strategy for generating passwords.
 * Implementations define how characters are chosen; the service
 * can swap strategies at runtime without changing client code.
 */
interface PasswordAlgorithm {
    String generate(int length);
}
