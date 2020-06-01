package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (variableListNode != null)
            variableListNode.accept(visitor);

        if (expressionListNode != null)
            expressionListNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (variableListNode != null)
            variableListNode.traverseTopDown(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (variableListNode != null)
            variableListNode.traverseBottomUp(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseBottomUp(visitor);

        accept(visitor);
    }

}
