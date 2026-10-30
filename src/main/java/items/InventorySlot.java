package items;

public class InventorySlot {
    private Item item;
    private int quantity;

    public InventorySlot(Item item, int quanity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        if (item.getStackable()) {
            this.quantity += quantity;
        } else
            IO.print("Det här föremålet går inte att ha flera av");
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) this.quantity = 0;
    }

    public String getName() {
        return item.getName();
    }

    public String getName() {
        return item.getName();
    }
}