package game;

import characters.Knight;
import game.save.GameSnapshot;
import game.save.InventoryEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnapshotRestorerTest {

    private GameSnapshot snapshot(int health) {
        return new GameSnapshot("Lancelott", health, 2,
                List.of("Insurgent"), List.of(new InventoryEntry("Sten", 3)));
    }

    @Test
    void restoreShouldRecreateTheHeroWithNameAndHealth() {
        Knight hero = SnapshotRestorer.restore(snapshot(70));
        assertEquals("Lancelott", hero.getName());
        assertEquals(70, hero.getHealth());
    }

    @Test
    void restoreShouldKeepFullHealthUnchanged() {
        assertEquals(100, SnapshotRestorer.restore(snapshot(100)).getHealth());
    }

    @Test
    void restoreShouldCapHealthAtStartingHealth() {
        assertEquals(100, SnapshotRestorer.restore(snapshot(150)).getHealth());
    }
}
