package items.potions;

import characters.Hero;
import items.Item;
import items.Potion;

public class SmallHealingPotion extends Item implements Potion {
    private final int healingAmount = 15;

    public SmallHealingPotion() {
        super("Hälsosmoothie", true);
    }

    @Override
    public void use(Hero hero) {
        hero.heal(healingAmount);
        hero.getInventory().removeItem(this, 1);

    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public PotionInfo getPotionInfo() {
        return new PotionInfo("Hälsosmoothie", "Helar hjälten med " + getHealingAmount() + " hp");
    }
}
