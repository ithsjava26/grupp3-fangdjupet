package items.potions;

import items.Item;
import items.Potion;

public class LargeHealingPotion extends Item implements Potion {
    private final int healingAmount = 35;

    public LargeHealingPotion(String name, boolean stackable) {
        super("Drakblod", true);//Sorry Tove we can change the name later. I know we decided not to have
        //fantasytheme :)
    }

    @Override
    public void use() {
        int healingAmount = 35;

    }

    public int healingAmount() {
        return this.healingAmount;
    }

    public PotionInfo getPotionInfo() {
        return new PotionInfo("Drakblod", "Helar hjälten med " + healingAmount() + " hp");
    }
}
