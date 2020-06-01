package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (functionName != null)
            functionName.accept(visitor);

        if (functionBody != null)
            functionBody.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (functionName != null)
            functionName.traverseTopDown(visitor);

        if (functionBody != null)
            functionBody.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (functionName != null)
            functionName.traverseBottomUp(visitor);

        if (functionBody != null)
            functionBody.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "function " + functionName + " " + functionBody;
    }
}
