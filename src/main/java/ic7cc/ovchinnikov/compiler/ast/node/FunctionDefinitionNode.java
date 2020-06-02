package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionDefinitionNode extends Statement {

    private FunctionName functionName;
    private FunctionBodyNode functionBody;

    public FunctionDefinitionNode(FunctionName functionName, FunctionBodyNode functionBody) {
        this.functionName = functionName;
        if (functionName != null)
            functionName.setParent(this);

        this.functionBody = functionBody;
        if (functionBody != null)
            functionBody.setParent(this);
    }

    @Override
    public String toString() {
        return "function " + functionName + " " + functionBody;
    }
}
