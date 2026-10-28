package items;

import items.weapons.WeaponInfo;

public interface Weapon {
    int makeDamage();

    boolean isRangedWeapon();
    WeaponInfo getWeaponInfo();


}
