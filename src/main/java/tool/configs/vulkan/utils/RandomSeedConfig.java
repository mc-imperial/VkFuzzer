package tool.configs.vulkan.utils;

import tool.configs.Config;

/**
 * Created by constantinos on 16/05/2016.
 */
public class RandomSeedConfig extends Config {
    private long randomSeed;

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }
}
