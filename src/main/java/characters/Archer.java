package characters;

public class Archer extends Enemy {

    public Archer() {
        super(60);
    }

    @Override
    public void presentEnemy() {
        IO.println("Du känner den kalla vinden i ansiktet efter att dörren kastats upp. Swooosh! En pil viner förbi " +
                "ditt ansikte och fastnar i dörren bakom dig. En bågskytt har fått syn på dig!");
    }

    @Override
    public void attack(Character hero) {
        int damage = 25;
        IO.println(getName() + " träffar dig och gör " + damage + " skada!");
        hero.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Bågskytten";
    }
}
