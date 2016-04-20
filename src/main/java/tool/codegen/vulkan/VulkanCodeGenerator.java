package tool.codegen.vulkan;

import tool.codegen.CodeGenerator;
import tool.codegen.coverage.Coverage;
import tool.configs.vulkan.VulkanGlobalState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 27/03/2016.
 */
public abstract class VulkanCodeGenerator extends CodeGenerator {
    protected final String RESULT = "result";
    private final String ID = "id";
    protected final VulkanGlobalState globalState;

    public VulkanCodeGenerator(final RandomStringGenerator randomStringGenerator,
                               final RandomNumberGanerator randomNumberGanerator,
                               final FreshMap freshMap,
                               final Coverage coverage,
                               final String template,
                               final VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                template);
        this.globalState = globalState;
    }

    protected int generateConfigId() {
        return freshMap.getFreshId(ID);
    }
}
