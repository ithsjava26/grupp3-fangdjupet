package game;

import characters.Enemy;
import characters.Hero;
import characters.Knight;
import characters.combat.Combat;
import game.events.GameEvent;
import game.events.GameEventListener;
import game.save.GameSnapshot;
import game.save.InventoryEntry;
import items.weapons.SmallStone;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {

    /** Combat stub where the hero always wins, without IO or randomness. */
    private Combat heroWinsCombat() {
        return new Combat() {
            @Override
            public void startCombat(Hero hero, Enemy enemy) {
                enemy.takeDamage(enemy.getHealth());
            }
        };
    }

    /** Combat stub where the hero always loses. */
    private Combat heroLosesCombat() {
        return new Combat() {
            @Override
            public void startCombat(Hero hero, Enemy enemy) {
                hero.takeDamage(hero.getHealth());
            }
        };
    }

    /** Listener that records event messages so tests can assert on them. */
    private static class RecordingListener implements GameEventListener {
        final List<String> messages = new ArrayList<>();

        @Override
        public void onEvent(GameEvent event) {
            messages.add(event.message());
        }
    }

    @Test
    void nextBattleShouldDefeatTheEnemyAndAdvanceToTheNextRoom() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 1, List.of());
        engine.nextBattle();
        assertEquals(2, engine.getRoomNumber());
        assertEquals(List.of("Stridshunden"), engine.snapshot().defeatedEnemies());
        assertTrue(engine.getHero().isAlive());
    }

    @Test
    void clearingEveryRoomShouldClearTheDungeon() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 1, List.of());
        for (int room = 1; room <= GameEngine.TOTAL_ROOMS; room++) {
            assertFalse(engine.isDungeonCleared());
            engine.nextBattle();
        }
        assertTrue(engine.isDungeonCleared());
        assertEquals(GameEngine.TOTAL_ROOMS, engine.snapshot().defeatedEnemies().size());
    }

    @Test
    void nextBattleShouldDoNothingOnAClearedDungeon() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 1, List.of());
        for (int room = 1; room <= GameEngine.TOTAL_ROOMS; room++) {
            engine.nextBattle();
        }
        RecordingListener listener = new RecordingListener();
        engine.addListener(listener);
        engine.nextBattle();
        assertTrue(engine.isDungeonCleared());
        assertEquals(GameEngine.TOTAL_ROOMS + 1, engine.getRoomNumber());
        assertEquals(1, listener.messages.size());
    }

    @Test
    void heroDeathShouldKeepTheRoomAndEndTheGame() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroLosesCombat(), 2, List.of("Stridshunden"));
        engine.nextBattle();
        assertEquals(2, engine.getRoomNumber());
        assertEquals(List.of("Stridshunden"), engine.snapshot().defeatedEnemies());
        assertFalse(engine.getHero().isAlive());
        assertTrue(engine.isGameOver());
    }

    @Test
    void snapshotShouldReflectProgressAfterAWonBattle() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 1, List.of());
        engine.nextBattle();
        GameSnapshot snapshot = engine.snapshot();
        assertEquals("Lancelott", snapshot.heroName());
        assertEquals(2, snapshot.roomNumber());
        assertEquals(List.of("Stridshunden"), snapshot.defeatedEnemies());
    }

    @Test
    void snapshotShouldIncludeTheHeroInventory() {
        Knight hero = new Knight("Lancelott");
        hero.getInventory().addItem(new SmallStone(), 2);
        GameEngine engine = new GameEngine(hero, heroWinsCombat(), 1, List.of());
        assertEquals(List.of(new InventoryEntry("Sten", 2)), engine.snapshot().inventory());
    }

    @Test
    void listenersShouldReceiveEventsFromTheBattle() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 1, List.of());
        RecordingListener listener = new RecordingListener();
        engine.addListener(listener);
        engine.nextBattle();
        assertTrue(listener.messages.stream().anyMatch(message -> message.contains("Stridshunden")));
        assertTrue(listener.messages.stream().anyMatch(message -> message.contains("besegrade")));
    }

    @Test
    void restoredEngineShouldContinueAtTheSavedRoom() {
        GameEngine engine = new GameEngine(new Knight("Lancelott"), heroWinsCombat(), 3,
                List.of("Stridshunden", "Rebellen"));
        engine.nextBattle();
        assertEquals(4, engine.getRoomNumber());
        assertEquals(List.of("Stridshunden", "Rebellen", "Bågskytten"), engine.snapshot().defeatedEnemies());
    }
}
