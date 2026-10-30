package items;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<InventorySlot> platser = new ArrayList<>();

    public void addItem(Item newItem, int Quantity) {

        if (!platser.stream().anyMatch(slot -> slot.getName().equals(newItem.getName()))) {
            platser.add(new InventorySlot(newItem, Quantity));
            return;

        }
        // if stackable, find spot whit same objekt that already exists
        if (newItem.getStackable()) {
            for (InventorySlot slot : platser) {
                if (slot.getItem().getName().equals(newItem.getName())) {
                    slot.increaseQuantity(Quantity);
                    return;
                }
            }
        }

    }

    public void printInventory() {
        for (InventorySlot slot : platser) {
            IO.println(slot.getItem().getName() + slot.getQuantity());
        }
    }
}