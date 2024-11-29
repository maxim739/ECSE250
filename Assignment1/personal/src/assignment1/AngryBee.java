package assignment1;

public class AngryBee extends HoneyBee{
    private int attackDamage;
    public static int BASE_HEALTH;  //Base Health of angry bees
    public static int BASE_COST;    //Base food cost
    public AngryBee(Tile insectPos, int attackDamage) {
        super(insectPos, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
    }

    public boolean takeAction(){
        // Positioned on path will attempt to sting a hornet
        // If not on path it wont do anything and returns false
        // Look for non empty swarm either on the same tile or on the next tile towards the nest
        // If finds it, inflicts damage onto the first hornet in the swarm equal to the dmg
        // cannot sting hornet that is on it's nest
        // if no hornet to be stung, and cannot do anything, return false
        // If bee stings hornet then return true
        // The bee ends its action on the same tile that it started on, so don't modify tile
        Tile r = this.getPosition();
        if(r.isOnThePath()){    // It is on the path so it will be able to sting
            if(r.getNumOfHornets() > 0){    // There is a hornet on THIS tile
                r.getHornet().takeDamage(attackDamage);
                return true;
            }
            else if ((r.towardTheNest().getNumOfHornets()>0) && !(r.isNest())){
                r.towardTheNest().getHornet().takeDamage(attackDamage); // Sting next hornet if not in nest
                return true;
            }
        }
        return false;
    }
}
