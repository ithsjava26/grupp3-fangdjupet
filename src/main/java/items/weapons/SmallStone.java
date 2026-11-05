package items.weapons;

import characters.Knight;
import items.Item;
import items.Weapon;

public class SmallStone extends Item implements Weapon {
    public SmallStone() {
        super("Sten", true);
    }

    @Override
    public int makeDamage() {
        return 5;
    }

    @Override
    public boolean isRangedWeapon() {
        return true;
    }

    public void use(Knight knight) {
        //TODO fix so this is connected to an enemy takes damage.
        knight.getInventory().removeItem(this, 1);
    }

    @Override
    public WeaponInfo getWeaponInfo() {
        return new WeaponInfo("Sten", isRangedWeapon(), makeDamage());
    }
}
