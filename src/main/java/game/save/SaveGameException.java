package game.save;

/**
 * Thrown when something goes wrong while saving or loading a game round.
 */
public class SaveGameException extends Exception {

    public SaveGameException(String message) {
        super(message);
    }

    public SaveGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
