package characters;

public abstract class Enemy extends AbstractCharacter {
    //Någon gemensam logik för alla enemies?/TW

    int makeDamage() {
        return 0;
    }

     abstract void presentEnemy();

}
