package characters;

abstract class AbstractCharacter implements Character {
    private int health;

    // -- turordning i strid? Ska väl vara gemensamt för både Knight och Enemy.../TW

    // -- Skulle vi ha ett enum för good or bad/ team eller boolean för isFriendly eller motsvarande? Borde nog
    //    placeras här och sen initieras i konstruktorn i de ärvda klasserna?/TW

    @Override
    public int getHealth() {
        return health;
    }

    //kanske inte ska vara av typen int, utan bero på vilket weapon som används? Dock delas takeDamage av Hero och Enemy
    // men Enemies tror jag inte kommer dela inventory med Hero, tänk igenom före så det funkar.
    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void attack() {

    }
}
