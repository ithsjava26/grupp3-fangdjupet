package items.weapons;

import items.Item;
import items.Weapon;

public class LongIronSword extends Item implements Weapon {
    public LongIronSword() {
        super("Långt Järnsvärd", false);
    }

    @Override
    public int makeDamage() {
        return 40;
    }

    @Override
    public boolean isRangedWeapon() {
        return false;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Långt Järnsvärd", isRangedWeapon(), makeDamage());
    }
}
