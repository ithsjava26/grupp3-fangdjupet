package items.weapons;

import items.Item;
import items.Weapon;

public class ShortIronSword extends Item implements Weapon {
    public ShortIronSword() {
        super("kort Järnsvärd", false);
    }

    @Override
    public int makeDamage() {
        return 20;
    }

    @Override
    public boolean isRangedWeapon() {
        return false;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Kort Järnsvärd", isRangedWeapon(), makeDamage());
    }
}
