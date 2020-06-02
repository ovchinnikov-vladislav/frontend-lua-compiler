package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionCallNode extends FunctionCall {

    private PrefixExpression preExp;
    private ExpressionListNode expressionListNode;

    public FunctionCallNode(PrefixExpression preExp, ExpressionListNode expressionListNode) {
        this.preExp = preExp;
        if (preExp != null)
            preExp.setParent(this);

        this.expressionListNode = expressionListNode;
        if (expressionListNode != null)
            expressionListNode.setParent(this);
    }

    @Override
    public String toString() {
        return preExp + " (" + expressionListNode + ")";
    }
}
