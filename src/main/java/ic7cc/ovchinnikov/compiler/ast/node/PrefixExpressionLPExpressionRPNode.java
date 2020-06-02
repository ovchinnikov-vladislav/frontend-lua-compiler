package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrefixExpressionLPExpressionRPNode extends PrefixExpression {

    private Expression expression;

    public PrefixExpressionLPExpressionRPNode(Expression expression) {
        this.expression = expression;
        if (expression != null)
            expression.setParent(this);
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
