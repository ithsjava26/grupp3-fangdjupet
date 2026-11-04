package items;

import items.armour.IronArmour;
import items.potions.LargeHealingPotion;
import items.potions.SmallHealingPotion;
import items.weapons.*;

public abstract class Item {
    private String name;
    private boolean stackable; // Indicates if you can stack multiple of this item, for example, armor no, potions yes.

    public Item(String name, boolean stackable) {
        this.name = name;
        this.stackable = stackable;
    }

    public Boolean getStackable() {
        return stackable;
    }

    public String getName() {
        return name;
    }

    public String getinfo() {
        return switch (this) {
            case Weapon weapon -> "|Typ:Vapen| " + "|Skada:" + weapon.getWeaponInfo().damage() + "|" +
                    " |Ranged:" + (weapon.getWeaponInfo().rangedWeapon() ? "Ja|" : "Nej|");
            case Armour armour -> "|Typ:Armour|" + "|Beskrivning:" + armour.getArmourInfo().description() + "|" +
                    "|Används:" + (armour.getArmourInfo().equipped() ? "Ja|" : "Nej|");
            case Potion potion -> "|Typ:dryck|" + "|Beskrivning:" + potion.getPotionInfo().description() + "|";
            default -> "";
        };
    }

    public static Item of(String item) {  // this will be the factory
        return switch (item) {
            case "Träpinne" -> new WoodenStick();
            case "Sten" -> new SmallStone();
            case "Kort Järnsvärd" -> new ShortIronSword();
            case "Långt Järnsvärd" -> new LongIronSword();
            case "Pilbåge" -> new Bow();
            case "Hälsosmoothie" -> new SmallHealingPotion();
            case "Drakblod" -> new LargeHealingPotion();
            case "Riddarrustning" -> new IronArmour();
            default -> throw new IllegalArgumentException("Invalid item name"); // We will have to make sure to spell
            // the items correctly (and mind Case sensitivity).
        };
    }

}

