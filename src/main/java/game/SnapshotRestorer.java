package game;

import characters.Knight;
import game.save.GameSnapshot;

/**
 * Restores the hero from a saved GameSnapshot.
 *
 * NOTE: Heroes always start at 100 hp, and AbstractCharacter has no way to set
 * health directly yet. For now we use takeDamage to bring the hero down to the
 * saved health
 *
 * // A setter must be added later on
 */
public class SnapshotRestorer {

    private static final int STARTING_HEALTH = 100;

    public static Knight restore(GameSnapshot snapshot) {
        Knight hero = new Knight(snapshot.heroName());
        int missingHealth = STARTING_HEALTH - snapshot.heroHealth();
        if (missingHealth > 0) {
            hero.takeDamage(missingHealth);
        }
        return hero;
    }
}
