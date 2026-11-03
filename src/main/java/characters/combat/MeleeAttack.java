package characters.combat;

import items.Weapon;
import characters.Character;

public class MeleeAttack implements AttackStrategy {
    private final Weapon weapon;

    public MeleeAttack(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int calculateDamage() {
        if (weapon.isRangedWeapon()) {
            IO.println("Dåligt val, motståndaren är alldeles för nära så vapnet orsakade ingen skada.");
            return 0;
        } else
            return weapon.makeDamage();
    }

    @Override
    public void executeAttack(Character hero, Character enemy) {

    }
}
