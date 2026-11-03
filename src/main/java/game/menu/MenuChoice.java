package game.menu;

/**
 * The main menu choices, parsed from the player's input.
 * The parsing lives here so it can be tested without a Scanner.
 */
public enum MenuChoice {

    NEW_ADVENTURE,
    CONTINUE,
    SHOW_EQUIPMENT,
    NEXT_BATTLE,
    EXIT,
    UNKNOWN;

    public static MenuChoice parse(String input) {
        if (input == null) {
            return UNKNOWN;
        }
        return switch (input.trim().toLowerCase()) {
            case "1" -> NEW_ADVENTURE;
            case "2" -> CONTINUE;
            case "3" -> SHOW_EQUIPMENT;
            case "4" -> NEXT_BATTLE;
            case "e" -> EXIT;
            default -> UNKNOWN;
        };
    }
}
