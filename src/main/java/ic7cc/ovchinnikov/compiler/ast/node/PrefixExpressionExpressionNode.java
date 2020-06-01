package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class PrefixExpressionExpressionNode extends PrefixExpression {

    public Expression expression;

    public PrefixExpressionExpressionNode(Expression expression) {
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
}
