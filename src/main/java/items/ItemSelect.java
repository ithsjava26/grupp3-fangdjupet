package items;

public class ItemSelect {

    public static int select(Inventory inventory) {
        do {
            inventory.printInventory();
            try {
                String choice = IO.readln("Välj föremål (nr): ");
                if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= inventory.size()) {
                    return Integer.parseInt(choice);
                } else
                    IO.println("Felaktig inmatning försök igen");
            } catch (Exception e) {
                IO.println("Felaktig inmatning försök igen");
            }
        } while (true);
    }

    public static Item returnItem(Inventory inventory, int index) {
        Item item = inventory.getSlot(index).getItem();
        switch (item) {
            case Weapon weapon -> {
                return (Item) weapon;
            }
            case Potion potion -> {
                return (Item) potion;
            }
            case Armour armour -> {
                return (Item) armour;
            }
            default -> {
                IO.println("Felaktig inmatning försök igen");
                return null;
            }


        }

    }
}

