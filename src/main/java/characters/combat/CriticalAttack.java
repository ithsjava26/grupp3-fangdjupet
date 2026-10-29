package characters.combat;

import items.Weapon;

public class CriticalAttack implements AttackStrategy {
// Tänker mig att denna attacken kanske också skadar hjältens health.


    @Override
    public int calculateDamage(Weapon weapon) {
        return 0;
    }
}
