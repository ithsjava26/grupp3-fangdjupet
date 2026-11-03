package characters;

public class Warhound extends Enemy {

    public Warhound() {
        super(40);
    }

    @Override
    public void presentEnemy() {
        IO.println("Du kommer ut på en liten, halmbeklädd gård. Du hör ett morrande, tittar till vänster och får " +
                "syn på en gigantisk, mörkgrå hund som rusar mot dig.");
    }

    @Override
    public void attack(Character target) {
        int damage = 10;
        IO.println(getName() + " biter dig, blodet rinner och du får " + damage + " skada.");
    }

    @Override
    public String getName() {
        return "Stridshunden";
    }
}