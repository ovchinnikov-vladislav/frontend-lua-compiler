package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentNode extends Statement {

    @JacksonXmlProperty(localName = "VarList")
    private VariableListNode variableListNode;
    @JacksonXmlProperty(localName = "ExpList")
    private ExpressionListNode expressionListNode;

    public AssignmentNode(VariableListNode variableListNode, ExpressionListNode expressionListNode) {
        this.variableListNode = variableListNode;
        if (variableListNode != null)
            variableListNode.setParent(this);

        this.expressionListNode = expressionListNode;
        if (expressionListNode != null)
            expressionListNode.setParent(this);
    }

    @Override
    public String toString() {
        return variableListNode + " = " + expressionListNode;
    }
}
