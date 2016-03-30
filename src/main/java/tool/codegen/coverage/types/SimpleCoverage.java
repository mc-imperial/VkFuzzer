package tool.codegen.coverage.types;

import tool.codegen.coverage.Coverage;

import java.util.Random;

/**
 * Created by constantinos on 27/03/2016.
 */
public class SimpleCoverage implements Coverage {
    private final Random random;
    private final double incrementValue;
    private double badConfigChance;

    public SimpleCoverage(final double badConfigChance,
                          final double incrementValue) {
        random = new Random();
        this.incrementValue = incrementValue;
        this.badConfigChance = badConfigChance;
    }

    @Override
    public boolean createGoodConfig() {
        boolean badConfig = random.nextGaussian() <= badConfigChance;
        badConfigChance += incrementValue;
        return badConfig;
    }
}
