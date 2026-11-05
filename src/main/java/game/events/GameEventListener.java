package game.events;

/**
 * Listens for game events emitted by the game engine (Observer pattern).
 */
public interface GameEventListener {

    void onEvent(GameEvent event);
}
