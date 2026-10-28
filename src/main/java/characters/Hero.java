package characters;

// Lägger till klass för att det ska gå att lägga till fler förkämpar ifall vi vill längre fram, men också för att
// matcha mönstret med AbstractCharacter -> Enemy -> Insurgent.
public abstract class Hero extends AbstractCharacter {
    private final String name;
    private int health;

    // Alla förkämpar startas med 100 healthpoints.
    public Hero(String name, int health) {
        this.name = name;
        this.health = 100;
    }

    // Bara förkämpar ska kunna heala.
    public int heal() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
