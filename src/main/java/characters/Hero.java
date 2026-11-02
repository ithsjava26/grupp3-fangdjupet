package characters;

import characters.combat.AttackStrategy;

public abstract class Hero extends AbstractCharacter {
    private final String name;
    private AttackStrategy attackStrategy;

    public Hero(String name) {
        super(100);
        this.name = name;
    }

    public void heal(int amount) {
        setHealth(getHealth() +  amount);
        if (getHealth() > 100)
            setHealth(100);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public void attack(Character target) {
        if (attackStrategy != null)
            attackStrategy.executeAttack(this, target);
        else
            IO.println(getName() + " har inga vapen!");
    }
}