package utilities;

import java.util.Random;

/**
 * Created by Leondria on 12/17/2014.
 */
public class RandomNumbers {

    public void RandomNumbers(){}

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
