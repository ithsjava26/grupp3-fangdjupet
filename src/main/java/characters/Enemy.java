package characters;

import items.weapons.WeaponInfo;

public abstract class Enemy extends AbstractCharacter {

    public Enemy(int health) {
        super(health);
    }

     public abstract void presentEnemy();

    @Override
    public abstract void attack(Character target);

    public boolean isWeaponEffective(WeaponInfo info) {
        return true;
    }
}
