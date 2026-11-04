package characters.combat;

import characters.Enemy;
import items.Weapon;
import characters.Character;
import items.weapons.WeaponInfo;

public class RangedAttack implements AttackStrategy {
    private final Weapon weapon;

    public RangedAttack(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int calculateDamage() {
        return weapon.makeDamage();
    }

    @Override
    public void executeAttack(Character hero, Character enemy) {
        WeaponInfo info = weapon.getWeaponInfo();

        if (enemy instanceof Enemy opponent) {
            if (!opponent.isWeaponEffective(info))
                return;
        }

        int damage = calculateDamage();
        IO.println(hero.getName() + " gör den bästa attack han kan komma på när han befinner sig så långt från sin " +
                "motståndare, med " + weapon.getWeaponInfo().name() + ".");

        enemy.takeDamage(damage);
        IO.println(enemy.getName() + " träffades och tog " + damage + " i skada. Fienden har " + enemy.getHealth() +
                "HP kvar.");
    }
}