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
        int damage = calculateDamage();
        IO.println(hero.getName() + " gör den bästa attack han kan komma på när han befinner sig så långt från sin " +
                "motståndare, med " + weapon.getWeaponInfo().name() + ".");
        if (damage > 0) {
            enemy.takeDamage(damage);
            IO.println(enemy.getName() + " träffades och tog " + damage + " i skada. Fienden har " + enemy.getHealth() +
                    "HP kvar.");
        }
    }
}