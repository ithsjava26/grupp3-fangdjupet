package characters.combat;

import characters.Enemy;
import items.Weapon;
import characters.Character;
import items.weapons.WeaponInfo;

public class MeleeAttack implements AttackStrategy {
    private final Weapon weapon;

    public MeleeAttack(Weapon weapon) {
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
        IO.println(hero.getName() + " anfaller med " + weapon.getWeaponInfo().name() + "!");

        enemy.takeDamage(damage);
        IO.println(enemy.getName() + " träffades och tog " + damage + " i skada. Fienden har " + enemy.getHealth() +
                "HP kvar.");
    }
}
