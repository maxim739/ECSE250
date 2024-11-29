package assignment1;

public class SniperBee extends HoneyBee{
    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public SniperBee(Tile insectPos, int attackDamage, int piercingPower){
        super(insectPos, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
        // this.BASE_HEALTH =
        // How do we get the base health and base cost fields filled out?
        //Base cost and food cost should always be the same so don't need them for constructor
    }

    public boolean takeAction(){
        // Needs tp be on path, if not on path then it returns false
        // If on path scans on path from hive to its tile for first non empty swarm
        // Inflicts attackdamage on all hornets in the swarm
        // on first n hornets in swarm where n is equal to piercing power or
        // the amount of hornets in swarm whichever is first
        // Take two turns to shoot
        // Should alternate between aiming and shooting
        // Returns true only when shot has been released
        // Cannot attack hornets in their nest
        Tile r = this.getPosition();
        Tile next = r.towardTheNest();
        if(r.isOnThePath()){    // It is on the path so it will be able to fire

        }
        return false;
    }

}
