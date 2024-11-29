package assignment1;

import assignment1.*;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        //Path will be current -> previous 1 -> previous 2
        Tile previousTile1 = new Tile();
        Tile previousTile2 = new Tile();
        Tile previousTile3 = new Tile();
        Tile currentTile = new Tile();
        Tile hiveTile = new Tile();
        Tile nestTile = new Tile();

        hiveTile.buildHive();
        nestTile.buildNest();

        hiveTile.setOnFire();
        nestTile.setOnFire();

        currentTile.debugSetOnPath();
        //System.out.println("1st createpath");
        currentTile.createPath(hiveTile, previousTile1);
        //System.out.println("2st createpath");
        previousTile1.createPath(currentTile, previousTile2);
        System.out.println(previousTile3);
        previousTile2.createPath(previousTile1, previousTile3);
        previousTile3.createPath(previousTile2, nestTile);

        FireBee firebee = new FireBee(previousTile2, 1);
        Hornet hornet1 = new Hornet(previousTile3, 20, 10);
        Hornet hornet2 = new Hornet(previousTile3, 20, 10);
        System.out.println(Arrays.toString(previousTile3.getHornets()));
        System.out.println(previousTile3.getNumOfHornets());
        System.out.println(hornet1.getHealth());
        System.out.println(previousTile3.getHornet().getPosition().removeInsect(hornet1));
        hornet1.takeDamage(25);
        System.out.println(hornet1.getPosition());

        //System.out.println(hornet1.equals(hornet2));    // ?
        System.out.println(previousTile3.getNumOfHornets());








    }
}