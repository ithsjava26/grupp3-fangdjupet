package characters.combat;

import characters.Hero;
import characters.Enemy;
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

                //TODO! Låta användaren välja att dricka potion eller välja vapen?

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
            //TODO! Vad händer när karaktären dör? Anpassa meddelandet efter det.
            IO.println("Du blev besegrad.");
    }
}
