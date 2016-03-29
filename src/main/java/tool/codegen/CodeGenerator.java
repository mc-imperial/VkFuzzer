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
    protected final Coverage coverage;
    protected final String template;

    public CodeGenerator(final RandomStringGenerator randomStringGenerator,
                         final RandomNumberGanerator randomNumberGanerator,
                         final FreshMap freshMap,
                         final Coverage coverage,
                         final String template) {
        this.randomStringGenerator = randomStringGenerator;
        this.randomNumberGanerator = randomNumberGanerator;
        this.freshMap = freshMap;
        this.coverage = coverage;
        this.template = template;
    }

    public abstract Config generateConfig();

    public String getTemplate() {
        return  template;
    }
}
