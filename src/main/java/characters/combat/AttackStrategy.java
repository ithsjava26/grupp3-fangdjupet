package characters.combat;

import items.Weapon;

public interface AttackStrategy {

    int calculateDamage(Weapon weapon);

}
