package items;

import characters.Knight;
import items.potions.PotionInfo;

public interface Potion {
    void use(Knight knight);

    PotionInfo getPotionInfo();

}
