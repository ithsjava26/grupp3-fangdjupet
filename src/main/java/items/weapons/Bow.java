package items.weapons;

import items.Item;
import items.Weapon;

public class Bow extends Item implements Weapon {
    public Bow() {
        super("Pilbåge", false);
    }

    @Override
    public int makeDamage() {
        return 25;
    }

    @Override
    public boolean isRangedWeapon() {
        return true;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Pilbåge", isRangedWeapon(), makeDamage());
    }
}
