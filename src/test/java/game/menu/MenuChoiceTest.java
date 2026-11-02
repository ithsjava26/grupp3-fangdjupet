package game.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuChoiceTest {

    @Test
    void numbersShouldMapToMenuChoices() {
        assertEquals(MenuChoice.NEW_ADVENTURE, MenuChoice.parse("1"));
        assertEquals(MenuChoice.CONTINUE, MenuChoice.parse("2"));
        assertEquals(MenuChoice.SHOW_EQUIPMENT, MenuChoice.parse("3"));
        assertEquals(MenuChoice.NEXT_BATTLE, MenuChoice.parse("4"));
    }

    @Test
    void exitShouldBeCaseInsensitive() {
        assertEquals(MenuChoice.EXIT, MenuChoice.parse("e"));
        assertEquals(MenuChoice.EXIT, MenuChoice.parse("E"));
    }

    @Test
    void whitespaceAroundInputShouldBeIgnored() {
        assertEquals(MenuChoice.NEXT_BATTLE, MenuChoice.parse(" 4 "));
    }

    @Test
    void unknownInputShouldGiveUnknownChoice() {
        assertEquals(MenuChoice.UNKNOWN, MenuChoice.parse("x"));
        assertEquals(MenuChoice.UNKNOWN, MenuChoice.parse(""));
        assertEquals(MenuChoice.UNKNOWN, MenuChoice.parse(null));
    }
}
