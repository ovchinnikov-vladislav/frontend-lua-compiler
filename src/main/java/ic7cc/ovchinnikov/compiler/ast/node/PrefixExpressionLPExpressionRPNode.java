package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (expression != null)
            expression.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (expression != null)
            expression.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (expression != null)
            expression.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
