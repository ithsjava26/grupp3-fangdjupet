package characters;

public class BadKingsguard extends Enemy {

    public BadKingsguard() {
        super(100);
    }

    @Override
    public void presentEnemy() {
        IO.println("I tronsalen står inte rebellerna, utan kungens egna livvakt. Han torkar sitt svärd omsorgsfullt, " +
                "vänder sig mot dig och säger: \"Välkommen till tronsalen. Kungen gör inte längre vad som är bäst " +
                "för riket. Någon annan behöver styra, då kan jag lika gärna bli rik samtidigt.\" ");
        IO.println("Kungens livvakt har förrått oss alla. Vi måste övermanna honom för att frita kungen!");
    }

    @Override
    public void attack(Character target) {

    }

    @Override
    public String getName() {
        return "Kungens livvakt";
    }
}
