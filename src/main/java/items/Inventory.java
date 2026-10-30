package items;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<InventorySlot> slot = new ArrayList<>();

    public void addItem(Item newItem, int quantity) {

        if (!slot.stream().anyMatch(slot -> slot.getName().equals(newItem.getName()))) {
            slot.add(new InventorySlot(newItem, quantity));
            return;

        }
        // if stackable, find spot whit same objekt that already exists
        if (newItem.getStackable()) {
            for (InventorySlot slot : slot) {
                if (slot.getItem().getName().equals(newItem.getName())) {
                    slot.increaseQuantity(quantity);
                    return;
                }
            }
        }

    }

    public void printInventory() {
        for (InventorySlot slot : slot) {
            IO.println(slot.getItem().getName() + slot.getQuantity());
        }
    }
}