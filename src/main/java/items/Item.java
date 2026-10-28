package items;

public abstract class Item {
    private String name;
    private boolean Stackable; // Indicates if you can stack multiple of this item, for example, armor no, potions yes.

    public Item(String name, boolean stackable) {
        this.name = name;
        this.Stackable = stackable;
    }
}

