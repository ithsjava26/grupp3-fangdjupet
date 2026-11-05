package game;

import characters.Archer;
import characters.BadKingsguard;
import characters.Enemy;
import characters.Hero;
import characters.Insurgent;
import characters.Knight;
import characters.Warhound;
import characters.combat.Combat;
import game.events.GameEvent;
import game.events.GameEventListener;
import game.save.GameSnapshot;
import game.save.InventoryEntry;
import items.Inventory;
import items.InventorySlot;
import items.weapons.ShortIronSword;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the dungeon as a sequence of rooms, one enemy per room.
 * The engine emits game events to its listeners (Observer pattern).
 */
public class GameEngine {

    /**
     * The current room number, 1-based. It stays within TOTAL_ROOMS while the
     * dungeon is uncleared and becomes TOTAL_ROOMS + 1 after the last victory.
     */
    public static final int TOTAL_ROOMS = 4;

    private final Hero hero;
    private final Combat combat;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final List<String> defeatedEnemies;
    private int currentRoom;

    /**
     * Creates a fresh run: a new knight with the starting sword equipped.
     */
    public static GameEngine newGame(String heroName) {
        Knight hero = new Knight(heroName);
        ShortIronSword startingSword = new ShortIronSword();
        hero.getInventory().addItem(startingSword, 1);
        hero.equipWeapon(startingSword, false);
        return new GameEngine(hero, 1, List.of());
    }

    /**
     * Rebuilds an engine from a loaded GameSnapshot, continuing at the saved room.
     */
    public GameEngine(Hero hero, int startRoom, List<String> defeatedEnemies) {
        this(hero, new Combat(), startRoom, defeatedEnemies);
    }

    GameEngine(Hero hero, Combat combat, int startRoom, List<String> defeatedEnemies) {
        this.hero = hero;
        this.combat = combat;
        this.currentRoom = startRoom;
        this.defeatedEnemies = new ArrayList<>(defeatedEnemies);
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public Hero getHero() {
        return hero;
    }

    public int getRoomNumber() {
        return currentRoom;
    }

    public boolean isDungeonCleared() {
        return currentRoom > TOTAL_ROOMS;
    }

    /**
     * True while the hero has no health left, i.e. the run has ended.
     */
    public boolean isGameOver() {
        return !hero.isAlive();
    }

    public void nextBattle() {
        if (isDungeonCleared()) {
            emit(hero.getName() + " har redan klarat Fangdjupet! Starta ett nytt äventyr för att spela igen.");
            return;
        }
        Enemy enemy = createEnemyForRoom(currentRoom);
        emit("Rum " + currentRoom + ": " + enemy.getName() + " väntar i mörkret!");
        combat.startCombat(hero, enemy);
        if (hero.isAlive()) {
            defeatedEnemies.add(enemy.getName());
            emit(hero.getName() + " besegrade " + enemy.getName() + "!");
            currentRoom++;
            if (isDungeonCleared()) {
                emit(hero.getName() + " har klarat alla rum i Fangdjupet!");
            }
        } else {
            emit(hero.getName() + " föll i rum " + currentRoom + "...");
        }
    }

    /**
     * The current game state as a snapshot, ready to save to file.
     */
    public GameSnapshot snapshot() {
        return new GameSnapshot(hero.getName(), hero.getHealth(), currentRoom,
                defeatedEnemies, inventoryEntries());
    }

    private Enemy createEnemyForRoom(int room) {
        return switch (room) {
            case 1 -> new Warhound();
            case 2 -> new Insurgent();
            case 3 -> new Archer();
            default -> new BadKingsguard();
        };
    }

    private List<InventoryEntry> inventoryEntries() {
        Inventory inventory = hero.getInventory();
        List<InventoryEntry> entries = new ArrayList<>();
        // getSlot is 1-based, matching the numbering the backpack printout shows
        for (int itemNr = 1; itemNr <= inventory.size(); itemNr++) {
            InventorySlot slot = inventory.getSlot(itemNr);
            entries.add(new InventoryEntry(slot.getItem().getName(), slot.getQuantity()));
        }
        return entries;
    }

    private void emit(String message) {
        GameEvent event = new GameEvent(message);
        for (GameEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
