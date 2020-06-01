package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ReturnNode extends ReturnStatement {

    @JacksonXmlProperty(localName = "ExpList")
    private ExpressionListNode expressionListNode;

    public ReturnNode(ExpressionListNode expressionListNode) {
        this.expressionListNode = expressionListNode;
        if (expressionListNode != null) expressionListNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (expressionListNode != null)
            expressionListNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (expressionListNode != null)
            expressionListNode.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "return " + expressionListNode;
    }
}
