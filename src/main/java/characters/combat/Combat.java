package characters.combat;

import characters.Hero;
import characters.Enemy;
import items.*;
import java.util.Random;

public class Combat {
    private final Random random = new Random();

    public void startCombat(Hero hero, Enemy enemy) {
        IO.println("En fight startar mot " + enemy.getName() + "!");
        enemy.presentEnemy();

        boolean isHeroTurn = random.nextBoolean();

        if (isHeroTurn)
            IO.println("Du var snabbast och får första chansen till attack!");
        else
            IO.println(enemy.getName() + " är snabbare och har redan påbörjat ett anfall mot dig!");

        while (hero.isAlive() && enemy.isAlive()) {
            if (isHeroTurn) {
                IO.println("--- DIN TUR ---");
                executeHeroTurn(hero, enemy);

                hero.attack(enemy);
            } else {
                IO.println("--- " + enemy.getName().toUpperCase() + "S TUR ---");
                enemy.attack(hero);
            }
            isHeroTurn = !isHeroTurn;
        }

        if (hero.isAlive())
            IO.println("Du besegrade " + enemy.getName() + "!");
        else
            IO.println("Du blev besegrad.");
    }

    private void executeHeroTurn(Hero hero, Enemy enemy) {
        IO.println("Din HP: " + hero.getHealth() + " | " + enemy.getName() + "s HP: " + enemy.getHealth());

        Inventory inventory = hero.getInventory();
        inventory.printInventory();

        handlePotionPhase(hero, inventory);
        handleWeaponAndAttackPhase(hero, enemy, inventory);
    }

    private void handlePotionPhase(Hero hero, Inventory inventory) {
        if (inventory.size() == 0) {
            return;
        }

        boolean hasPotion = false;
        for (int i = 1; i <= inventory.size(); i++) {
            if (inventory.getSlot(i).getItem() instanceof Potion) {
                hasPotion = true;
                break;
            }
        }

        if (!hasPotion) {
            return;
        }

        String choice = IO.readln("Vill du dricka en healingpotion innan du anfaller? (ja/nej): ");
        if (choice.equalsIgnoreCase("j")) {
            int slotNr = readInt("Ange nummer för den potion du vill dricka: ");

            if (slotNr >= 1 && slotNr <= inventory.size()) {
                InventorySlot slot = inventory.getSlot(slotNr);
                Item item = slot.getItem();

                if (item instanceof Potion potion) {
                    potion.use(hero);
                    inventory.removeItem(item, 1);
                    IO.println("Du drack " + potion.getPotionInfo().name() + "! Din HP är nu " + hero.getHealth() + ".");
                } else {
                    IO.println("Föremålet du valde är inte en healingpotion. Du hoppar över healing-fasen.");
                }
            } else {
                IO.println("Ogiltigt föremålsnummer. Du hoppar över healing-fasen.");
            }
        }
    }

    private void handleWeaponAndAttackPhase(Hero hero, Enemy enemy, Inventory inventory) {
        if (inventory.size() == 0) {
            IO.println("Du har inget i ditt inventory!");
            hero.attack(enemy);
            return;
        }

        IO.println("VÄLJ VAPEN FÖR ATTACK");
        int slotNr = readInt("Ange nummer på vapnet du vill använda: ");

        Weapon chosenWeapon = null;

        if (slotNr >= 1 && slotNr <= inventory.size()) {
            Item item = inventory.getSlot(slotNr).getItem();
            if (item instanceof Weapon weapon) {
                chosenWeapon = weapon;
            } else {
                IO.println("Det valda föremålet är inte ett vapen!");
            }
        }

        if (chosenWeapon == null) {
            for (int i = 1; i <= inventory.size(); i++) {
                if (inventory.getSlot(i).getItem() instanceof Weapon weapon) {
                    chosenWeapon = weapon;
                    IO.println("Väljer automatiskt " + weapon.getWeaponInfo().name() + " från ditt inventory.");
                    break;
                }
            }
        }

        if (chosenWeapon == null) {
            IO.println("Du har inga vapen i ryggsäcken!");
            hero.attack(enemy);
            return;
        }

        IO.println("Välj anfallstyp:");
        IO.println("1. Vanlig attack");
        IO.println("2. Kritisk attack (dubbel skada, men kostar egen HP)");

        int attackChoice = readInt("Ditt val (1-2): ");

        boolean isCritical = (attackChoice == 2);

        hero.equipWeapon(chosenWeapon, isCritical);
        hero.attack(enemy);
    }

    private int readInt(String text) {
        while (true) {
            try {
                String input = IO.readln(text);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                IO.println("Ogiltigt nummer, försök igen");
            }
        }
    }
}
