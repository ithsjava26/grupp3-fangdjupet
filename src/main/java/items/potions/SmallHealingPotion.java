package items.potions;

import characters.Knight;
import items.Item;
import items.Potion;

public class SmallHealingPotion extends Item implements Potion {
    private final int healingAmount = 15;

    public SmallHealingPotion() {
        super("Hälsosmoothie", true);
    }

    @Override
    public void use(Knight knight) {
        knight.heal(healingAmount);
        //knight.inventory.decreaseQuantity(1);
        //hur ska den här kopplingen göras? ska vi ha ett fält i TheKnightSaga som heter Inventory som man kan
        //acsessa med en getter eller har ni något annat bra förslag?
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public PotionInfo getPotionInfo() {
        return new PotionInfo("Hälsosmoothie", "Helar hjälten med " + getHealingAmount() + " hp");
    }
}
