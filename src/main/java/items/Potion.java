package items;

import characters.Hero;
import items.potions.PotionInfo;

public interface Potion {
    void use(Hero hero);

    PotionInfo getPotionInfo();

}
