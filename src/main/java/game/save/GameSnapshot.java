package game.save;

import java.util.List;
import java.util.Objects;

/**
 * All game state that gets saved to file for an ongoing round.
 * A record that both the save system and the game engine agree on.
 */
public record GameSnapshot(String heroName,
                           int heroHealth,
                           int roomNumber,
                           List<String> defeatedEnemies,
                           List<InventoryEntry> inventory) {

    public GameSnapshot {
        Objects.requireNonNull(heroName, "heroName");
        if (heroName.isBlank()) {
            throw new IllegalArgumentException("Hjälten måste ha ett namn");
        }
        if (heroHealth < 0) {
            throw new IllegalArgumentException("Hälsopoäng kan inte vara negativa");
        }
        if (roomNumber < 1) {
            throw new IllegalArgumentException("Rumnummer börjar på 1");
        }
        defeatedEnemies = List.copyOf(defeatedEnemies);
        inventory = List.copyOf(inventory);
    }
}
