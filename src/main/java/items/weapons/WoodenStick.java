package items.weapons;

import items.Item;
import items.Weapon;

public class WoodenStick extends Item implements Weapon {

    public WoodenStick() {
        super("Träpinne", false);
    }

    @Override
    public int makeDamage() {
        return 10;
    }

    @Override
    public boolean isRangedWeapon() {
        return false;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Träpinne", isRangedWeapon(), 10);
    }
}
