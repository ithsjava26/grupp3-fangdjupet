package items.weapons;

import items.Item;
import items.Weapons;

public class SmallStone extends Item implements Weapons {
    public SmallStone() {
        super("Sten", true);
    }

    @Override
    public int makeDamage() {
        return 5;
    }

    @Override
    public boolean isRangedWeapon() {
        return true;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Sten", isRangedWeapon(), makeDamage());
    }
}
