package characters;

public class Insurgent extends Enemy {

    public Insurgent(int health) {
        super(50);
    }

    @Override
    public void presentEnemy() {
        IO.println("You are now standing face to face with an " + getName() + ". The leader, in fact.");
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
