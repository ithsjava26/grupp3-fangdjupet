package characters;

public abstract class Enemy extends AbstractCharacter {

    public Enemy(int health) {
        super(health);
    }

    int makeDamage() {
        return 0;
    }

     public abstract void presentEnemy();

}
