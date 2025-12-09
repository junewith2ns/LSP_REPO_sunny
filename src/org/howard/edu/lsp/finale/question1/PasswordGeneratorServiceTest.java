package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests covering Singleton identity, precondition behavior,
 * and each algorithm's output contract.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
        // Ensure a clean slate for every test.
        service._resetForTests();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() must not return null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Must be the exact same object in memory
        assertSame(service, second, "Singleton instances must be identical (same reference).");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        s._resetForTests(); // ensure no algorithm selected
        assertThrows(IllegalStateException.class, () -> s.generatePassword(5));
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertEquals(10, p.length(), "Length must match request.");
        assertTrue(p.matches("\\d{10}"), "Basic must contain digits only.");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertEquals(12, p.length(), "Length must match request.");
        assertTrue(p.matches("[A-Za-z0-9]{12}"),
                "Enhanced must be alphanumeric (A–Z, a–z, 0–9).");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertEquals(8, p.length(), "Length must match request.");
        assertTrue(p.matches("[A-Za-z]{8}"), "Letters must contain letters only.");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("\\d{10}"));

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]{10}"));

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]{10}"));
    }
}
