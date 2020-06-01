package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionCallSelfNode extends FunctionCall {

    private PrefixExpression preExp;
    private String name;
    private ExpressionListNode expressionListNode;

    public FunctionCallSelfNode(PrefixExpression preExp, String name, ExpressionListNode expressionListNode) {
        this.preExp = preExp;

        if (preExp != null)
            preExp.setParent(this);

        this.name = name;

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
        if (preExp != null)
            preExp.accept(visitor);

        if (expressionListNode != null)
            expressionListNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (preExp != null)
            preExp.traverseTopDown(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (preExp != null)
            preExp.traverseBottomUp(visitor);

        if (expressionListNode != null)
            expressionListNode.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return preExp + ":" + name  + " " + expressionListNode;
    }

}
