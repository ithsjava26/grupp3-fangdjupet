package characters.combat;

import items.Weapon;
import characters.Character;

public class RangedAttack implements AttackStrategy {
    private final Weapon weapon;

    public RangedAttack(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int calculateDamage() {
        if (!weapon.isRangedWeapon()) {
            IO.println("Dåligt val, vapnet funkar inte på avstånd!");
            return 0;
        } else
            return weapon.makeDamage();
    }

    @Override
    public void executeAttack(Character hero, Character enemy) {

    }
}