package assignment1;

public class FireBee extends HoneyBee{
    private int attackRange;    // Attack range of the bee
    public static int BASE_HEALTH;  // Base health
    public static int BASE_COST;    // Base food cost

    public FireBee(Tile insectPos, int attackRange){
        //super(insectPos, healthPoints, foodCost);
        super(insectPos, BASE_HEALTH, BASE_COST);
        this.attackRange = attackRange;
    }

    public boolean takeAction(){
        // If on path, exclusivley target tiles occupied by hornets within their range
        // Not previously attacked by a fire bee
        // Between all possible targets set fire to first encountered tile from hive to nest
        // If not positioned on path then does not do anything
        // Can target tile with other bees on it, but not the one it itself is on
        // Cannot attack hornet positioned within their nest
        //System.out.println("we are in takeaction");
        Tile r = this.getPosition();    // Current tile position of the firebee
        Tile next = r.towardTheNest();  // Next tile to the nest
        // If next is null then it isnt on path so terminate
        //System.out.println("This is" + r);
        //System.out.println("This is next" + next);
        if(r.isOnThePath()){    // It is on the path so it will be able to fire
            int x = this.attackRange;
            //System.out.println("Attack Range: " + x);
            int w = 0;
            while(w < x){
                // While loop to keep moving towards the nest unit it runs out of range or reaches nest
                if(next == null){   // The next tile is null so it is not on path, we cannot do anything to it
                    //System.out.println("next is null");
                    return false;
                }
                if(next.isNest() || next.isHive()){
                    //System.out.println("the tile is nest");
                    return false;
                }
//                System.out.println("This tile is: " + next.isOnFire());
//                System.out.println("This tile has: " + next.getHornet());

                if(!next.isOnFire() && next.getHornet()!=null){
                    //System.out.println("we are all set");
                    next.setOnFire();
                    return true;
                }
                next = next.towardTheNest();
                w++;
            }   // We are out of range to keep looking for tiles to attack, or we reached the nest

        }
        return false;
    }
}
