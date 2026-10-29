package characters;

import characters.combat.AttackStrategy;

public abstract class Hero extends AbstractCharacter {
    private final String name;
    private int health;
    private AttackStrategy attackStrategy;

    public Hero(String name) {
        this.name = name;
        this.health = 100;
    }

    public int heal() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}