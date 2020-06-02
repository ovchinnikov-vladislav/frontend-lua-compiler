package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrefixExpressionNode extends Expression {

    private PrefixExpression preExp;

    public PrefixExpressionNode(PrefixExpression preExp) {
        this.preExp = preExp;

        if (preExp != null)
            preExp.setParent(this);
    }

    @Override
    public String toString() {
        return preExp.toString();
    }
}
