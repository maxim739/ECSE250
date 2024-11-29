package assignment1;

public class Hornet extends Insect {
    private int attackDamage;
    // Some tricky stuff, will have to ask if this is legal
    // for now move forwards and impliment logic anyways
    // Will doing it this way still reach the tile that I want?
    // Want to make sure I reach the tile that the hornet belongs to, otherwise I will be accessing nothing
    public static int BASE_FIRE_DMG;
    private static boolean isQueen;    // Should be static or nonstatic?
    private static int howManyQueens;  // Should be static or nonstatic
    private boolean queenBool = false;

    public Hornet(Tile insectPos, int healthPoints, int attackDamage) {
        super(insectPos, healthPoints);
        //System.out.println("We are in hornet constructor");
        this.attackDamage = attackDamage;
        //insectPos.addInsect(this);
    }

    public boolean takeAction(){
        boolean whatToReturn = false;
        Tile r = this.getPosition();    //Our tile we are working w
        // If bee on same tile then hornet inflicts damage equal to attack damage on the bee
        // If no bee it takes a step forwards to the next tile closer to the bee hive
        // Tile and position of hornet must all be updated
        // If stings or moves forwards it must return true
        // If hornet is already on the bee hive and there are no more bees to kill then return false
        // Assume hornet never not on path towards hive
        // Hornet either stings or moves, if stings, dosent move forwards

        if(r.isOnFire()){
            this.takeDamage(BASE_FIRE_DMG);
        }

        if(r.getBee() != null){ // There is a bee on the tile
            r.getBee().takeDamage(attackDamage);    // Bee is returned and it takes damage
            whatToReturn = true;    // true because we did some damage
        }
        else{   // There isn't a bee on the tile so move forwards
            Tile next = r.towardTheHive();
            if(r.isHive() || next == null){  // We are on hive, or end of the line
                whatToReturn = false;   // return false because there are no more bees and we are on hive
            }
            else{   // There are no bees and there is a valid spot to move forwards to so we move
                this.setPosition(next); // Will now set the current position to one tile closer to hive
                whatToReturn = true;    // return true because we moved forwards
            }
        }
        if(this.isQueen){
            // Will take action twice
            // If on tile without moving on fire take damage twice
            if(queenBool==false){   // 1st time running through
                this.queenBool = true;
                this.takeAction();
            }
            if(queenBool==true){    // Second time running through the code
                this.queenBool = false; // Reset the variable
            }
        }
        return whatToReturn;
    }

    public boolean equals(Object object){
        Hornet hor = (Hornet) object;
        if(this.attackDamage == hor.attackDamage){  // They have the same attack damage, good start
            return super.equals(object);
        }
        return false;
    }

    public boolean isTheQueen(){return this.isQueen;}
    public void promote(){
        if(this.howManyQueens == 0){
            this.isQueen = true;
            this.howManyQueens += 1;
        }
    }
}
