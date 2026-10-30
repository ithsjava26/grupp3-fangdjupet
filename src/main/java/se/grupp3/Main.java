package se.grupp3;

import characters.Knight;
import items.Inventory;
import items.Item;
import items.weapons.SmallStone;
import items.weapons.WoodenStick;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fangdjupet");
        System.out.println("==========");

        Knight knight = new Knight("Lancelott");

        Inventory backPack = new Inventory();
        Item woodenstick = new WoodenStick();
        Item smallStone = new SmallStone();
        backPack.addItem(woodenstick, 1);
        backPack.addItem(smallStone, 2);
        backPack.printInventory();
        //This Doesn't work yet.
        backPack.addItem(woodenstick, 3);
        backPack.printInventory();
    }
}
