package tool.utils;

import java.util.Random;

/**
 * Created by constantinos on 25/03/2016.
 * A class that generate random strings
 */
public class RandomStringGenerator {
    private final Random random;
    private final int DEFAULT_LENGTH = 80;
    private final char[] CHARACTERS =
    {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
        'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9'
    };

    public RandomStringGenerator() {
        random = new Random();
    }

    // Generates a String with length size
    public String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int randomIndex = random.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[randomIndex]);
        }

        return sb.toString();
    }

    // Generates a String with DEFAULT_LENGTH length
    public String generateRandomString() {
        return generateRandomString(DEFAULT_LENGTH);
    }

    // Generates a String between 0 to length characters
    public String generateVariableRandomString(int length) {
        return generateRandomString(random.nextInt(length + 1));
    }

    // Generates a String between 0 to DEFAULT_LENGTH characters
    public String generateVariableRandomString() {
        return generateRandomString(random.nextInt(DEFAULT_LENGTH + 1));
    }
}
