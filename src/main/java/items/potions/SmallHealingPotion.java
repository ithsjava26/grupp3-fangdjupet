package items.potions;

import items.Item;
import items.Potion;

public class SmallHealingPotion extends Item implements Potion {
    public SmallHealingPotion() {
        super("Hälsosmoothie :)", true);
    }

    @Override
    public void use() {
        int healingAmount = 15;
        //TODO Heal the knight Instantly through Knights Heal() nethod?
        // set the healingpotion -1 in inventory(not yet implemented)

    }

    public int getHealingAmount() {
        return 15;
    }
}
