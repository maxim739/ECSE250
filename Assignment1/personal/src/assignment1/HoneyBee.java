package assignment1;

abstract class HoneyBee extends Insect{
    private int foodCost;
    public static double HIVE_DMG_REDUCTION; // Percentage of damage reduction bees get for being on hive
    public HoneyBee(Tile insectPos, int healthPoints, int foodCost){
        super(insectPos, healthPoints);
        this.foodCost = foodCost;
    }

    @Override
    public void takeDamage(int t){
        Insect r = (Insect) this;
        Tile s = this.getPosition();
        if(s.isHive()){
            // Reduce damage taken
            double z = 1-HIVE_DMG_REDUCTION;
            //System.out.println(z);
            t = (int) (t*z);
            super.takeDamage(t);
        }
        else{
            // Regular amount of damage taken
            super.takeDamage(t);
        }
    }

    public boolean takeAction(){
        return false;
    }

    public int getCost(){
        return this.foodCost;
    }
}