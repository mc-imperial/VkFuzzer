package tool.utils;

import java.util.Random;

/**
 * Created by constantinos on 25/03/2016.
 * A class that generates random numbers
 */
public class RandomNumberGanerator {
    private final Random random;

    public RandomNumberGanerator() {
        random = new Random();
    }

    // Returns a random number from 0 to Integer.Max_Value - 1
    public int randomNumber() {
        return randomNumber(Integer.MAX_VALUE);
    }

    // Returns a random number from 0 to maxValue - 1
    public int randomNumber(int maxValue) {
        return random.nextInt(maxValue);
    }
}
