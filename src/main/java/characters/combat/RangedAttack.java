package characters.combat;

import items.Weapon;

public class RangedAttack implements AttackStrategy {

//    Kan ha olika beräkningsmodeller för att skapa olika mycket skada även om det är samma vapen som används.

    @Override
    public int calculateDamage(Weapon weapon) {
        return weapon.makeDamage();
    }
}
