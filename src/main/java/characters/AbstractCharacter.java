package characters;

public abstract class AbstractCharacter implements Character {
    private int health;

    public AbstractCharacter(int health) {
        this.health = health;
    }

//TODO: turordning i strid?

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0)
            health -= damage;
    }

    @Override
    public void attack(Character target) {

    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}