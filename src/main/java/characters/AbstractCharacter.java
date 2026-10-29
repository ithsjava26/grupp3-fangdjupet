package characters;

abstract class AbstractCharacter implements Character {
    private int health;

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