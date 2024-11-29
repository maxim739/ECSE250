package assignment1;

public class FireBee extends HoneyBee{
    private int attackRange;    // Attack range of the bee
    public static int BASE_HEALTH;  // Base health
    public static int BASE_COST;    // Base food cost

    public FireBee(Tile insectPos, int attackRange){
        //super(insectPos, healthPoints, foodCost);
        super(insectPos, BASE_HEALTH, BASE_COST);
        this.attackRange = attackRange;
//        this.BASE_HEALTH = healthPoints;
//        this.BASE_COST = foodCost;
        //Base cost and food cost should always be the same so don't need them for constructor
    }

    public boolean takeAction(){
        // If on path, exclusivley target tiles occupied by hornets within their range
        // Not previously attacked by a fire bee
        // Between all possible targets set fire to first encountered tile from hive to nest
        // If not positioned on path then does not do anything
        // Can target tile with other bees on it, but not the one it itself is on
        // Cannot attack hornet positioned within their nest
        Tile r = this.getPosition();
        Tile next = r.towardTheNest();
        if(r.isOnThePath()){    // It is on the path so it will be able to fire
            int x = this.attackRange;
            int i = 0;
            while(i <= x){
                // While loop to keep moving towards the nest unit it runs out of range or reaches nest
                if(!(next.isOnFire()) && !next.isNest() && (next.getNumOfHornets() > 0)){
                    next.setOnFire();
                    return true;
                }
                next = next.towardTheNest();    // Move the tile one more tile towards the nest
                i++;
            }   // We are out of range to keep looking for tiles to attack, or we reached the nest

        }
        return false;
    }
}
