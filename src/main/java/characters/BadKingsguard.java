package characters;

import items.weapons.WeaponInfo;

public class BadKingsguard extends Enemy {

    public BadKingsguard() {
        super(100);
    }

    @Override
    public void presentEnemy() {
        IO.println("""
                I tronsalen står inte rebellerna, utan kungens egna livvakt. Han torkar
                sitt svärd omsorgsfullt, vänder sig mot dig och säger: \"Välkommen till
                tronsalen. Kungen gör inte längre vad som är bäst för riket. Någon annan
                behöver styra, då kan jag lika gärna bli rik samtidigt.\" Kungens livvakt har
                förrått oss alla. Vi måste övermanna honom för att frita kungen!
                """);
    }

    @Override
    public void attack(Character hero) {
        int damage = 40;
        IO.println(getName() + " träffar dig med sitt tunga svärd och gör " + damage + " skada.");
        hero.takeDamage(damage);
    }

    @Override
    public String getName() {
        return "Livvakten";
    }

    @Override
    public boolean isWeaponEffective(WeaponInfo info) {
        if (info.damage() < 25) {
            IO.println(getName() + "s tunga rustning skyddar från skadan!");
            return false;
        }
        return true;
    }
}
