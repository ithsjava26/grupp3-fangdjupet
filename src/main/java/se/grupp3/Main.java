package se.grupp3;

import items.weapons.WoodenStick;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fangdjupet");
        System.out.println("==========");
        IO.print("Hello World!");
        WoodenStick woodenStick = new WoodenStick();
        IO.println(woodenStick.getWeaponInfo());
    }
}
