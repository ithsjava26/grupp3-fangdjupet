package se.grupp3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleTest {
    private List<String> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
    }

    @Test
    void newListIsEmpty() {
        assertTrue(items.isEmpty());
    }

    @Test
    void addIncreasesSize() {
        items.add("Sword");

        assertEquals(1, items.size());
    }

    @Test
    void getWithInvalidIndexThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> items.get(0));
    }
}
