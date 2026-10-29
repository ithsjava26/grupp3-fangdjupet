package items;

public class InventorySlot {
    private Item item;
    private int kvantitet;

    public InventorySlot(Item item, int kvantitet) {
        this.item = item;
        this.kvantitet = kvantitet;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return kvantitet;
    }

    public void increaseQuantity(int Quantity) {
        if (item.getStackable()) {
            this.kvantitet += Quantity;
        } else
            IO.print("Det här föremålet går inte att ha flera av");
    }

    public void decreaseQuantity(int Quantity) {
        this.kvantitet -= Quantity;
        if (this.kvantitet < 0) this.kvantitet = 0;
    }
}