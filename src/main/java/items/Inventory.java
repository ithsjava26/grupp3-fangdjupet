package items;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<InventorySlot> slot = new ArrayList<>();

    public void addItem(Item newItem, int quantity) {
        if (!newItem.getStackable())//Sets nonstackable items to quantity of 1 so they can't be created with quantity
            // over 1.
            quantity = 1;
        //if object is NOT already in inventory, add new slot.
        if (!slot.stream().anyMatch(slot -> slot.getName().equals(newItem.getName()))) {
            slot.add(new InventorySlot(newItem, quantity));
            return;

        }
        // if stackable, find spot with same objekt that already exists and add the quantity that's been put in as an arg.
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
        IO.println("=====================Ryggsäcken============================");
        int index = 1;
        for (InventorySlot currentSlot : slot) {
            IO.println("Nr:" + index + " Namn:" + currentSlot.getItem().getName().toUpperCase() +
                    " Antal:" + currentSlot.getQuantity());
            IO.println(currentSlot.getItem().getinfo());
            IO.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            index++;
        }
        IO.println("===========================================================");
    }


    public int size() {
        return slot.size();
    }

    public int getQuantity(int index) {
        return slot.get(index).getQuantity();
    }

    public void removeItem(Item item, int quantity) {
        for (InventorySlot current : slot) {
            if (current.getName().equals(item.getName())) {
                current.decreaseQuantity(quantity);
                if (current.getQuantity() == 0) {
                    slot.remove(current);   // safe: we return right after
                }
                return;
            }
        }
    }
}