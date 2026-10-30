package characters;

public abstract class AbstractCharacter implements Character {
    private int health;

    public AbstractCharacter(int health) {
        this.health = health;
    }

//TODO: turordning i strid?

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void attack() {

    }

    public boolean isAlive() {
        return health > 0;
    }
}