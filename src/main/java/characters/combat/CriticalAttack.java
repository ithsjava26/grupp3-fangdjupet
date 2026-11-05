package characters.combat;

import characters.Enemy;
import items.Weapon;
import characters.Character;
import items.weapons.WeaponInfo;

public class CriticalAttack implements AttackStrategy {
    private final Weapon weapon;

    public CriticalAttack(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int calculateDamage() {
        int damage = weapon.makeDamage();
        return damage * 2;
    }

    @Override
    public void executeAttack(Character hero, Character enemy) {
        WeaponInfo info = weapon.getWeaponInfo();

        if (enemy instanceof Enemy opponent) {
            if (!opponent.isWeaponEffective(info))
                return;
        }

        int damage = calculateDamage();
        int selfDamage = weapon.makeDamage() / 3;

        IO.println(hero.getName() + " samlar all sin kraft och gör en kritisk attack med " +
                weapon.getWeaponInfo().name() + "!");

        enemy.takeDamage(damage);
        IO.println(enemy.getName() + " tar " + damage + " i skada! Fienden har " + enemy.getHealth() + "HP kvar.");

        hero.takeDamage(selfDamage);
        IO.println("Den kritiska attacken är effektiv, men kostar. " + hero.getName() + " tappar " + selfDamage +
                "HP. " + hero.getHealth() + "HP kvar.");
    }
}
