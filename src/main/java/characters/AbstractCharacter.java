package characters;

public abstract class AbstractCharacter implements Character {
    private int health;

    public AbstractCharacter(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= Math.abs(damage);
        if (this.health < 0 )
            this.health = 0;
    }

    @Override
    public abstract void attack(Character target);

    public boolean isAlive() {
        return this.health > 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}