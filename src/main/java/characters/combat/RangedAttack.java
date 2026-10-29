package characters.combat;

import items.Weapon;

public class RangedAttack implements AttackStrategy {

    @Override
    public int calculateDamage(Weapon weapon) {
        if (!weapon.isRangedWeapon()) {
            IO.println("Dåligt val, vapnet funkar inte på avstånd!");
            return 0;
        } else
            return weapon.makeDamage();
    }
}