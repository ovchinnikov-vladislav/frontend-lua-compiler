package ic7cc.ovchinnikov.compiler.ast.node;

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
    public String toString() {
        return preExp + ":" + name  + " (" + expressionListNode +")";
    }

}
