package items;

import items.weapons.WeaponInfo;

public interface Weapons {
    int makeDamage();

    boolean isRangedWeapon();
    WeaponInfo getWeaponInfo();


}
