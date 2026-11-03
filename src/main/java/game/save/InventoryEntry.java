package game.save;

/**
 * An item stored in a save file: its name and how many the player has.
 */
public record InventoryEntry(String itemName, int quantity) {

    public InventoryEntry {
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("Föremålet måste ha ett namn");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Kvantiteten måste vara minst 1");
        }
    }
}
