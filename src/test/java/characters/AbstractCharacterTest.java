package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Syftet är att enbart testa skadeberäkning, ej andra delar i klassen.
 */
class AbstractCharacterTest {
    private Insurgent insurgent;

    @BeforeEach
    void createNewInsurgent() {
        insurgent = new Insurgent();
    }

    @Test
    void takeDamageReducesHealth() {
        insurgent.takeDamage(15);

        var result = insurgent.getHealth();

        assertEquals(35, result);
    }

    @Test
    void healthShouldNotDecreaseBelowZero() {
        insurgent.takeDamage(100);

        var result = insurgent.getHealth();

        assertEquals(0, result);
    }

    @Test
    void characterIsNotAliveWhenHealthIsZero() {
        insurgent.takeDamage(50);

        assertFalse(insurgent.isAlive());
    }

    @Test
    void takeDamageConvertsNegativeArgumentToStillMakeDamage() {
        insurgent.takeDamage(-20);

        var result = insurgent.getHealth();

        assertEquals(30, result);
    }




}