package characters;

import characters.combat.*;
import items.Inventory;
import items.Weapon;

public abstract class Hero extends AbstractCharacter {
    private final String name;
    private AttackStrategy attackStrategy;
    private final Inventory inventory = new Inventory();

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

    public Inventory getInventory() {
        return inventory;
    }

    public void equipWeapon(Weapon weapon, boolean useCriticalAttack) {
        if (useCriticalAttack) {
            setAttackStrategy(new CriticalAttack(weapon));
            IO.println(getName() + " samlar all sin styrka för ett kraftanfall");
        } else if (weapon.isRangedWeapon()) {
            setAttackStrategy(new RangedAttack(weapon));
            IO.println(getName() + " gör sig redo att avfyra " + weapon.getWeaponInfo().name() + ".");
        } else {
            setAttackStrategy(new MeleeAttack(weapon));
            IO.println(getName() + " höjer " + weapon.getWeaponInfo().name() + " för närstrid.");
        }
    }
}