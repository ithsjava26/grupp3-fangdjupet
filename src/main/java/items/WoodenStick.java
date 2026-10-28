package items;

public class WoodenStick extends Item implements Weapons {

    public WoodenStick() {
        super("Träpinne", false);
    }

    @Override
    public int makeDamage() {
        return 10;
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Träpinne", false, 10);
    }
}
