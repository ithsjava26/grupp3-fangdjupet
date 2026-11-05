package game;

import characters.Hero;
import characters.Knight;
import game.save.GameSnapshot;
import game.save.InventoryEntry;
import items.Inventory;
import items.Item;
import items.Weapon;

import java.util.List;

/**
 * Restores the hero from a saved GameSnapshot: name, health and inventory.
 */
public class SnapshotRestorer {

    /** A restored hero can never be healthier than a fresh one. */
    private static final int STARTING_HEALTH = 100;

    public static Knight restore(GameSnapshot snapshot) {
        Knight hero = new Knight(snapshot.heroName());
        hero.setHealth(Math.min(snapshot.heroHealth(), STARTING_HEALTH));
        restoreInventory(hero, snapshot.inventory());
        return hero;
    }

    /**
     * Re-creates the saved items with the item factory (Item.of) and equips
     * the first weapon found.
     */
    private static void restoreInventory(Hero hero, List<InventoryEntry> entries) {
        Inventory inventory = hero.getInventory();
        Weapon firstWeapon = null;
        for (InventoryEntry entry : entries) {
            Item item = createItem(entry.itemName());
            if (item == null) {
                continue;
            }
            inventory.addItem(item, entry.quantity());
            if (firstWeapon == null && item instanceof Weapon weapon) {
                firstWeapon = weapon;
            }
        }
        if (firstWeapon != null) {
            hero.equipWeapon(firstWeapon, false);
        }
    }

    private static Item createItem(String name) {
        try {
            return Item.of(name);
        } catch (IllegalArgumentException e) {
            return null; // unknown name in an old or hand-edited save file
        }
    }
}
