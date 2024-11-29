package assignment1;

public class Tile {
    private int foodTile;   // Food present on tile
    private boolean beeHive;    // Bee hive on tile
    private boolean hornetNest; // Hornet nest on tile
    private boolean hiveNestPath;   // Is this tile on the path between the two
    private Tile referenceTowardsHive;   // Null if not on path or at end of it
    // Refrence for next tile from hornet nest to the bee hive
    private Tile referenceTowardsNest;  // Null if not on path or at end of it
    // Reference for next tile from bee hive to hornets nest
    private HoneyBee beeOnTile; // The bee positioned on the tile
    private SwarmOfHornets hornetsOnTile;   // All of the hornets on the tile
    private boolean onFire;

    public Tile(){
        this.beeHive = false;
        this.hornetNest = false;
        this.hiveNestPath = false;
        this.foodTile = 0;
        this.beeOnTile = null;
        this.hornetsOnTile = new SwarmOfHornets();  // Just created new swarm, update rest to match
        this.referenceTowardsNest = null;
        this.referenceTowardsHive = null;
        this.onFire = false;
    }

    public Tile(int f, boolean beeHive, boolean hornetNest,
                boolean hiveNestPath, Tile referenceTowardsHive,
                Tile referenceTowardsNest, HoneyBee beeOnTile,
                SwarmOfHornets hornetsOnTile){
        this.foodTile = f;
        this.beeHive = beeHive;
        this.hornetNest = hornetNest;
        this.hiveNestPath = hiveNestPath;
        this.beeOnTile = beeOnTile;
        this.referenceTowardsHive = referenceTowardsHive;
        this.referenceTowardsNest = referenceTowardsNest;
        this.hornetsOnTile = hornetsOnTile;
    }

    public void debugSetOnPath(){
        this.hiveNestPath = true;
    }

    public void createPath(Tile tilePathToHive, Tile tilePathToNest){
        // Can have null as one of the two inputs
        // Should only occur when has hive or nest on it or at extremities of path that leads from one to the other
        // End means it either has refrence towards nest or reference to hive, not both?
        // Test to see if tile is at the end of the path

        String errMsg = "These fields are null when they shouldn't be!";
        if(tilePathToHive == null && tilePathToNest == null){
            throw new IllegalArgumentException(errMsg);
        }
        if(tilePathToHive == null){
            //System.out.println("The path to hive field is null");
            this.referenceTowardsNest = tilePathToNest;
            this.hiveNestPath = true;
            if(!tilePathToNest.isNest()){
                tilePathToNest.hiveNestPath = true;
            }
        }
        else if(tilePathToNest == null){
            System.out.println("The path to nest field is null");
            this.referenceTowardsHive = tilePathToHive;
            this.hiveNestPath = true;
            if(!tilePathToHive.isHive()){
                tilePathToHive.hiveNestPath = true;
            }
        }
        else{
            System.out.println("None of the fields are null");
            this.referenceTowardsHive = tilePathToHive;
            this.referenceTowardsNest = tilePathToNest;
            this.hiveNestPath = true;
            if(!tilePathToHive.isHive()){
                tilePathToHive.hiveNestPath = true;
            }
            else if(!tilePathToNest.isNest()){
                tilePathToNest.hiveNestPath = true;
            }
        }
    }

    public int collectFood(){
        int food = this.foodTile;
        this.foodTile = 0;
        return food;
    }

    public boolean addInsect(Insect insect){
        //System.out.println("We are in addinsect");
        //Takes insect and adds to tile
        // Bee only added if there are no other bees on the tile
        // No bee positioned on the hornet's nest
        // return true if sucessfully placed on a tile, false otherwise
        // Make sure that the insect is now updated itself to be positioned on the tile
        if(insect instanceof HoneyBee){
            //System.out.println("bee instance");
            if((this.getBee() == null) && !this.isNest()){
                insect.setPosition(this);
                this.beeOnTile = (HoneyBee) insect;
                return true;
            }
        }

        // Add to swarm if hornets already on tile
        // Add regular hornet if no other hornets already on the tile
        // Only if on path to hive, including both
        if(insect instanceof Hornet){
            //System.out.println("We realize that we want to add hornet");
            if(this.isOnThePath() || this.isHive() || this.isNest()){
                //System.out.println("We are in the thick");
                this.hornetsOnTile.addHornet((Hornet) insect);
                return true;
            }
        }
        return false;
    }

    public boolean removeInsect(Insect insect){
        //Tile death = new Tile();
        // Returns true of false on whether the operation was successful
        // Chenge the properties of the tile and the insect
        // Update insect position to be null
        // Compare insects directly using their references
        if(insect instanceof HoneyBee){
            this.beeOnTile = null;
            //insect.setPosition(null);
            return true;
        }
        if(insect instanceof Hornet){
            this.hornetsOnTile.removeHornet((Hornet) insect);
            return true;
        }
        return false;
    }

    public boolean isHive(){return this.beeHive;}   // Is there a bee hive on this tile
    public boolean isNest(){return this.hornetNest;}    // Is there a hornet nest on this tile
    public void buildHive(){this.beeHive = true;this.hiveNestPath=false;}    // Builds a bee hive on this tile
    public void buildNest(){this.hornetNest = true;this.hiveNestPath=false;}    // Build hornet nest on tile
    public boolean isOnThePath(){return this.hiveNestPath;}
    public Tile towardTheHive(){return this.referenceTowardsHive;}
    public Tile towardTheNest(){return this.referenceTowardsNest;}
    public void storeFood(int foodRecieved){this.foodTile = this.foodTile + foodRecieved;}
    public int getNumOfHornets(){return this.hornetsOnTile.sizeOfSwarm();}
    public HoneyBee getBee(){return this.beeOnTile;}
    public Hornet getHornet(){return this.hornetsOnTile.getFirstHornet();}
    public Hornet[] getHornets(){return this.hornetsOnTile.getHornets();}
    public void setOnFire(){this.onFire = true;}
    public boolean isOnFire(){return this.onFire;}

}
