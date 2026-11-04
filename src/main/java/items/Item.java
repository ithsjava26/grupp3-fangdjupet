package items;

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

}

