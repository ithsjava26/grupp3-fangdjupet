package characters.combat;

import characters.Character;

public interface AttackStrategy {

    int calculateDamage();

    void executeAttack(Character hero, Character enemy);
}
