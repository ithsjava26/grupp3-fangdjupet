package items.potions;

import items.Item;
import items.Potion;

public class SmallHealingPotion extends Item implements Potion {
    private final int healingAmount = 15;

    public SmallHealingPotion() {
        super("Hälsosmoothie", true);
    }

    @Override
    public void use() {

        //TODO Heal the knight Instantly through Knights Heal() nethod?
        // set the healingpotion -1 in inventory(not yet implemented)

    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public PotionInfo getPotionInfo() {
        return new PotionInfo("Hälsosmoothie", "Helar hjälten med " + getHealingAmount() + " hp");
    }
}
