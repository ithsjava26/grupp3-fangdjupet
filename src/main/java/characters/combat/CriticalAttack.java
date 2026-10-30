package characters.combat;

import items.Weapon;

public class CriticalAttack implements AttackStrategy {

    //TODO: denna metod ska också kosta hjälten health, kanske hälften av vad vapnet som används orsakar? Ifall det
    // blir för krångligt så kanske 5-10 healthPoints.
    @Override
    public int calculateDamage(Weapon weapon) {
        int damage = weapon.makeDamage();
        return damage * 2;
    }
}
