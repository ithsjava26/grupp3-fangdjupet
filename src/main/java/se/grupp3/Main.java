package se.grupp3;

import items.Item;
import items.weapons.WoodenStick;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fangdjupet");
        System.out.println("==========");
        IO.print("Hello World!");
        Item woodenStick = new WoodenStick();
        IO.println(((WoodenStick) woodenStick).getWeaponInfo());
    }
}
