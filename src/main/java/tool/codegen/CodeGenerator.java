package tool.codegen;

import tool.codegen.coverage.Coverage;
import tool.configs.Config;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 27/03/2016.
 */
public abstract class CodeGenerator {
    protected final RandomNumberGanerator randomNumberGanerator;
    protected final RandomStringGenerator randomStringGenerator;
    protected final FreshMap freshMap;
    private final Coverage coverage;

    public CodeGenerator(final RandomStringGenerator randomStringGenerator,
                         final RandomNumberGanerator randomNumberGanerator,
                         final FreshMap freshMap,
                         final Coverage coverage) {
        this.randomStringGenerator = randomStringGenerator;
        this.randomNumberGanerator = randomNumberGanerator;
        this.freshMap = freshMap;
        this.coverage = coverage;
    }

    public Config generateConfig() {
        return coverage.createGoodConfig() ?
                generateGoodConfig() : generateBadConfig();
    }

    public abstract Config generateGoodConfig();
    public abstract Config generateBadConfig();
}
