package items;

import items.weapons.SmallStone;
import items.weapons.WoodenStick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryTest {
    Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void addItemShouldAddItemToInventory() {
        inventory.addItem(new WoodenStick(), 1);

        assertEquals(1, inventory.size());
    }

    @Test
    void addingNonStackableItemShouldnotIncreaseInventorySize() {
        inventory.addItem(new WoodenStick(), 1);
        inventory.addItem(new WoodenStick(), 1);

        assertEquals(1, inventory.size());
    }

    @Test
    void addingStackableItemShouldNotIncreaseInventorySize() {
        inventory.addItem(new SmallStone(), 1);
        inventory.addItem(new SmallStone(), 1);


        assertEquals(1, inventory.size());
    }

    @Test
    void addingNonStackableItemShouldNotIncreaseQuantity() {
        inventory.addItem(new WoodenStick(), 1);
        inventory.addItem(new WoodenStick(), 1);
        inventory.addItem(new WoodenStick(), 2);

        assertEquals(1, inventory.getQuantity(0));
    }

    @Test
    void CreatingNonStackableItemWithQuantityOver1ShouldStillReturnQuantityOf1() {
        inventory.addItem(new WoodenStick(), 2);

        assertEquals(1, inventory.getQuantity(0));
    }

    @Test
    void addingStackableItemShouldIncreaseQuantity() {
        inventory.addItem(new SmallStone(), 1);
        inventory.addItem(new SmallStone(), 1);
        inventory.addItem(new SmallStone(), 2);

        assertEquals(4, inventory.getQuantity(0));
    }
    @Test
    void printInventoryShouldNotThrowExceptionWhenEmpty() {
        inventory.printInventory();

        assertDoesNotThrow(() -> inventory.printInventory());
    }
}