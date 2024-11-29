package assignment1;

abstract class Insect {
    private Tile insectPos;
    private int healthPoints;

    public Insect(Tile insectPos, int healthPoints){
        // When insect created that it is also added to corresponding tile
        // If already bee on tile, not able to add, etc
        //System.out.println("We are in insect constructor");
        this.insectPos = insectPos;
        //System.out.println(this.insectPos);
        //System.out.println("We are making boolean");
        boolean r = this.insectPos.addInsect(this);
        System.out.println(r);

        //System.out.println("We just added the hornet to the tile: " + r);
        if(!r){
            String msg = "You cannot move that insect there.";
            throw new IllegalArgumentException(msg);
        }
        //System.out.println("We are about to addinsectthis");
        //this.insectPos.addInsect(this);
        this.healthPoints = healthPoints;
    }

    final Tile getPosition(){
        return insectPos;
    }
    final int getHealth(){
        return healthPoints;
    }
    public void setPosition(Tile insectPos){this.insectPos = insectPos;}

    public void takeDamage(int t){
        // Update the damage to the insect
        // If health is 0 or below remove the insect from the tile
        this.healthPoints = this.healthPoints - t;
        //System.out.println(this.healthPoints);
        if(this.healthPoints<=0){
//            if(this instanceof Hornet){
//                System.out.println("Trying to remove hornet");
//            }
            //System.out.println("Trying to remove insect");
            this.insectPos.removeInsect(this);
            this.setPosition(null);
        }
    }

    abstract boolean takeAction();

    public boolean equals(Object obj){
        if(null==obj || !(obj instanceof Insect)) return false;

        Insect insect = (Insect) obj;
        return insect.insectPos.equals(this.insectPos) &&
                (insect.healthPoints == this.healthPoints);
    }

    public void regenerateHealth(double p){
        // Take insect health and adjusts it based on the current percentage
        // Round towards 0
        int t = (int) (this.healthPoints * p);
        this.healthPoints = this.healthPoints + t;
    }

}
