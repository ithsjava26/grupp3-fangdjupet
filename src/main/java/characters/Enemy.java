package characters;

public abstract class Enemy extends AbstractCharacter {

    public Enemy(int health) {
        super(health);
    }

    public int makeDamage() {
        return 0;
    }

     public abstract void presentEnemy();

    @Override
    public abstract void attack(Character target);
}
