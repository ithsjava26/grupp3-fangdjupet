package characters;

public class Insurgent extends Enemy {

    public Insurgent() {
        super(50);
    }

    @Override
    public void presentEnemy() {
        IO.println("""
                Dörren gnisslar när den öppnas och avslöjar en skepnad i mörkret. Skepnaden
                tar ett kliv framåt. """ + getName() + """
                , klädd enbart i trasiga kläder
                och med ett bestämt ansiktsuttryck. Du ska inte komma ut från det här rummet...
                """);
    }

    @Override
    public void attack(Character hero) {
        int damage = 15;
        IO.println(getName() + " attackerar dig och gör " + damage + " skada!");
        hero.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Rebellen";
    }
}
