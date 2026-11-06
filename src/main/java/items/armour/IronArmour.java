package items.armour;

import items.Armour;
import items.Item;

public class IronArmour extends Item implements Armour {
    private boolean equipped;

    public IronArmour() {
        super("Riddarrustning", false);
    }


    @Override
    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }


    public ArmourInfo getArmourInfo() {
        return new ArmourInfo("Riddarrustning",
                "Minskar alla skador med 50%", isEquipped());
    }
}
