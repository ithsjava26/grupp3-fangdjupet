package characters;

public class Insurgent extends Enemy {

    public Insurgent() {
        super(50);
    }

    @Override
    public void presentEnemy() {
        IO.println("Dörren gnisslar när den öppnas och avslöjar en skepnad i mörkret. Han tar ett kliv framåt. " +
                getName() + ", klädd enbart i trasiga kläder men med ett bestämt ansiktsuttryck. Du ska inte komma" +
                "ut från det här rummet...");
    }

    @Override
    public void attack(Character goodGuy) {
        int damage = 20;
        IO.println("Rebellen attackerar dig och gör " + damage + " skada!");
        goodGuy.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Rebellen";
    }
}
