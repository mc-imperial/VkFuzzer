package tool.codegen.coverage;

import tool.codegen.coverage.types.SimpleCoverage;

import java.util.Random;

/**
 * Created by constantinos on 30/03/2016.
 * Randomizes Coverage objects
 */
public class CoverageRandomizer {
    private final double SIMPLE_INITIAL_BOUND = 0.10;
    private final double SIMPLE_INCREMENT_BOUND = 0.01;
    private final Random random;
    private final CoverageType[] types;

    public CoverageRandomizer() {
        random = new Random();
        types = CoverageType.values();
    }

    // Returns a random Coverage object
    public Coverage randomCoverage() {
        CoverageType type = types[random.nextInt(types.length)];

        switch (type) {
            case SIMPLE:
                return randomSimpleCoverage();
            default:
                // Should never hit this
                return randomSimpleCoverage();
        }
    }

    // Creates a random simple coverage object
    private Coverage randomSimpleCoverage() {
        // Randomise
        double initialChance = random.nextDouble() * SIMPLE_INITIAL_BOUND;
        double incrementValue = random.nextDouble() * SIMPLE_INCREMENT_BOUND;

        return new SimpleCoverage(initialChance, incrementValue);
    }
}
