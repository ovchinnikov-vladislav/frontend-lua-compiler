package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldLeftRightExpressionNode extends Field {

    private Expression leftExpression;
    private Expression rightExpression;

    public FieldLeftRightExpressionNode(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        if (leftExpression != null)
            leftExpression.setParent(this);

        this.rightExpression = rightExpression;
        if (rightExpression != null)
            rightExpression.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (leftExpression != null)
            leftExpression.accept(visitor);

        if (rightExpression != null)
            rightExpression.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (leftExpression != null)
            leftExpression.traverseTopDown(visitor);

        if (rightExpression != null)
            rightExpression.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (leftExpression != null)
            leftExpression.traverseBottomUp(visitor);

        if (rightExpression != null)
            rightExpression.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "[" + leftExpression + "] = " + rightExpression;
    }
}
