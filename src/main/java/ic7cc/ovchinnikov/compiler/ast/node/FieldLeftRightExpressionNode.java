package ic7cc.ovchinnikov.compiler.ast.node;

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
    public String toString() {
        return "[" + leftExpression + "] = " + rightExpression;
    }
}
