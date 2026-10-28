package characters;

public class Insurgent extends Enemy {
    //Specifika egenskaper för insurgents/upprorsmakare/rebeller./TW

    @Override
    public void presentEnemy() {
        IO.println("You are now standing face to face with an " + getName() + ". The leader, in fact.");
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
