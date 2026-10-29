package characters.combat;

import items.Weapon;

public class MeleeAttack implements AttackStrategy {

    @Override
    public int calculateDamage(Weapon weapon) {
        return weapon.makeDamage();
    }
}
