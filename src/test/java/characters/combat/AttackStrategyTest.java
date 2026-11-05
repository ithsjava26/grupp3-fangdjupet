package characters.combat;

import items.*;
import items.weapons.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackStrategyTest {
    private Weapon shortIronSword;
    private Weapon bow;

    @BeforeEach
    void createTwoWeapons() {
        shortIronSword = new ShortIronSword();
        bow = new Bow();
    }

    @Test
    void weaponShouldMakeItsOriginalDefinedDamageWhenMeleeAttack() {
        AttackStrategy melee = new MeleeAttack(shortIronSword);

        var shortIronSwordDamage = shortIronSword.makeDamage();
        var result = melee.calculateDamage();

        assertEquals(shortIronSwordDamage, result);
    }

    @Test
    void rangedAttackWithRangedWeaponShouldMakeDefinedDamage() {
        AttackStrategy ranged = new RangedAttack(bow);

        var bowDamage = bow.makeDamage();
        var result = ranged.calculateDamage();

        assertEquals(bowDamage, result);
    }

    @Test
    void criticalAttackMakesDoubleWeaponDamage() {
        AttackStrategy critical = new CriticalAttack(shortIronSword);

        var criticalDamage = shortIronSword.makeDamage() * 2 ;
        var result = critical.calculateDamage();

        assertEquals(criticalDamage, result);
    }
}