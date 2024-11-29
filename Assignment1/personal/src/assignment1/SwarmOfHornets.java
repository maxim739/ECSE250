package assignment1;

public class SwarmOfHornets {
    // This is basically an implimentation of the arraylist example we saw in class
    private Hornet[] hornets;   // We already have the type of hornet that we want to store
    private int swarmSize = 0;  // Holds the size of the list
    public static double QUEEN_BOOST;   // Percentage of health regeneration

    public SwarmOfHornets(){
        hornets = new Hornet[10];   // Creates empty array with null slots for 10 hornets in future
        swarmSize = 0;  // Amount of hornets actually inside of the array
    }

    public int sizeOfSwarm(){
        // Returns the amount of hornets that are a part of this swarm
        return this.swarmSize;
    }

    public Hornet[] getHornets(){
        // Returns an array containing all of the hornets that are a part of the swarm
        // Need to make sure we use size so that we don't return any null points
        Hornet[] slice = new Hornet[this.swarmSize];    // Make new array that is the size of the swarm
        for(int i=0;i<swarmSize;i++){   // Loop and add the elements from hornets to the slice that will be returned
            slice[i] = this.hornets[i];
        }
        return slice;
    }

    public Hornet getFirstHornet(){
        // No inputs, returns first hornet to join the swarm, between those that are currently a part of it
        // Could be first in the array if we always add from the back
        // Should we create an int that gets shifted around to indicate which hornet has been in the swarm the longest?
        // No hornets then return null, dont remove any from the swarm
        if(swarmSize > 0){
            return hornets[0];
        }
        else{
            return null;
        }

    }

    public void addHornet(Hornet h){
        // Takes input hornet and adds hornet to end of queue for hornets in this swarm
        // May not be enough space for the hornet so we may need to increase the size of the array
        // No hornet rejected from swarm
        // No hornet removed from swarm when adding a new one
        // Need a resize method
        //System.out.println("In add hornet");
        if(hornets.length == swarmSize){
            // System.out.println("We need to resize now");
            resize();
        }

        hornets[swarmSize] = h; // Put the new hornet into the newly opened spot
        swarmSize = swarmSize + 1;  // Change the size first so that we move the new hornet into an empty spot
//        System.out.println("sucessfully added");
//        System.out.println(swarmSize);
        if(h.isTheQueen()){   // queen
            for(int i = 0; i<(swarmSize-1); i++){
                if(!hornets[i].isTheQueen()){
                    hornets[i].regenerateHealth(QUEEN_BOOST);
                }
            }
        }
    }

    private void resize(){
        Hornet[] bigger = new Hornet[hornets.length*2];
        for(int i = 0; i<swarmSize; i++){
            bigger[i] = this.hornets[i];
        }
        this.hornets = bigger;
    }

    public boolean removeHornet(Hornet h){
        // removes the first occurence (hornets are in array based on order of which they joined) of specified element
            // From this swarm
        // If no hornet exists it returns false, otherwise remove it and return true
        // Should compare hornets using directly their reference
            // remove any dependency between this method and your implimentation of equals()
        for(int i = 0; i<(swarmSize); i++){
            if(h.equals(hornets[i])){   // Goes until we find the first one equal to the other
                for(int k = i; k<(swarmSize-1); k++){   // Go through any hornets later in line and move them one up
                    hornets[k] = hornets[k+1];
                }
                this.swarmSize = this.swarmSize - 1;    // reduce swarm size by one now
                return true;    // Break so we don't try to find another instance
            }
        }
        return false;   // No hornets matched
    }
}
