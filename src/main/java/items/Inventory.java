package items;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<InventorySlot> platser = new ArrayList<>();

    public void addItem(Item newItem, int Quantity) {
        // if stackable, find spot whit same objekt that already exists
        if (newItem.getStackable()) {
            for (InventorySlot slot : platser) {
                if (slot.getItem().getName().equals(newItem.getName())) {
                    slot.increaseQuantity(Quantity);
                    return;
                }
            }
        } else if (!newItem.getStackable()) {//TODO fix so It doesnt create new slot for existing nonstackableobjekt.
            // If it does not exist or not stackable create new slot.
            platser.add(new InventorySlot(newItem, Quantity));
        }
    }

    public void printInventory() {
        for (InventorySlot slot : platser) {
            IO.println(slot.getItem().getName() + slot.getQuantity());
        }
    }
}