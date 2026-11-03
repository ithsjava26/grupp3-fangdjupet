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
        int damage = calculateDamage();
        IO.println(hero.getName() + " anfaller " + enemy.getName() + " med " + weapon.getWeaponInfo().name() + "!");
        if (damage > 0) {
            enemy.takeDamage(damage);
            IO.println(enemy.getName() + " träffades och tog " + damage + " i skada. Fienden har " + enemy.getHealth() +
                    "HP kvar.");
        }
    }
}
