package characters;

public interface Character {

    void attack(Character target);
    void takeDamage(int damage);
    int getHealth();
    String getName();
    boolean isAlive();
}
