package characters;

import characters.combat.AttackStrategy;

public abstract class Hero extends AbstractCharacter {
    private final String name;
    private int health;
    private AttackStrategy attackStrategy;

    public Hero(String name) {
        super(100);
        this.name = name;
    }

    public void heal() {

    }

    @Override
    public String getName() {
        return name;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}