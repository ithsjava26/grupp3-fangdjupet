package game.events;

/**
 * Logs game events to the console.
 */
public class ConsoleGameLogger implements GameEventListener {

    @Override
    public void onEvent(GameEvent event) {
        IO.println(event.message());
    }
}
