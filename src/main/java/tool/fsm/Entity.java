package tool.fsm;

import tool.codegen.CodeGenerator;

/**
 * Created by constantinos on 30/03/2016.
 */
public interface Entity {
    public void reset();
    public boolean didReachStop();
    public CodeGenerator getCurrentCodeGenerator();
    public String getGeneratedProgram();
}
