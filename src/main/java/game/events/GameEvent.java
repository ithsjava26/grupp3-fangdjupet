package game.events;

/**
 * Something that happened during the game, sent to every GameEventListener.
 */
public record GameEvent(String message) {
}
