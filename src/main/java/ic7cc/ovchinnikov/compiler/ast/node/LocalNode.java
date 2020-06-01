package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalNode extends Statement {

    @JacksonXmlProperty(localName = "NameList")
    private NameListNode nameListNode;
    @JacksonXmlProperty(localName = "ExpList")
    private ExpressionListNode expressionListNode;

    public LocalNode(NameListNode nameListNode, ExpressionListNode expressionListNode) {
        this.nameListNode = nameListNode;
        if (nameListNode != null)
            nameListNode.setParent(this);

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
        if (nameListNode != null)
            nameListNode.accept(visitor);

        if (expressionListNode != null)
            expressionListNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (nameListNode != null)
            nameListNode.traverseTopDown(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameListNode != null)
            nameListNode.traverseBottomUp(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "local" + nameListNode + " = " + expressionListNode;
    }
}
