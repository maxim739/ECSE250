package assignment1;

public class BusyBee extends HoneyBee {
    public static int BASE_HEALTH;
    public static int BASE_COST;
    public static int BASE_AMOUNT_COLLECTED;    // Amount of food busy bees collect

    public BusyBee(Tile insectPos){
        super(insectPos, BASE_HEALTH, BASE_COST);
    }

    public boolean takeAction(){    // Add amount of food collected to the tile
        this.getPosition().storeFood(BASE_AMOUNT_COLLECTED);
        return true;
    }
}