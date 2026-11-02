package characters.combat;

import items.Weapon;
import characters.Character;

public class CriticalAttack implements AttackStrategy {
    private final Weapon weapon;

    public CriticalAttack(Weapon weapon) {
        this.weapon = weapon;
    }

    //TODO: denna metod ska också kosta hjälten health, kanske hälften av vad vapnet som används orsakar? Ifall det
    // blir för krångligt så kanske 5-10 healthPoints.
    @Override
    public int calculateDamage() {
        int damage = weapon.makeDamage();
        return damage * 2;
    }

    @Override
    public void executeAttack(Character hero, Character enemy) {

    }
}
