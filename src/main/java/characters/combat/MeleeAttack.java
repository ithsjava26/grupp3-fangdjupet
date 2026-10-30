package characters.combat;

import items.Weapon;

public class MeleeAttack implements AttackStrategy {

    @Override
    public int calculateDamage(Weapon weapon) {
        if (weapon.isRangedWeapon()) {
            IO.println("Dåligt val, motståndaren är alldeles för nära så vapnet orsakade ingen skada.");
            return 0;
        } else
            return weapon.makeDamage();
    }
}
