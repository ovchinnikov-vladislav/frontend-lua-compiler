package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldExpressionNode extends Field {

    private Expression fieldExpression;

    public FieldExpressionNode(Expression fieldExpression) {
        this.fieldExpression = fieldExpression;
        if (fieldExpression != null)
            fieldExpression.setParent(this);
    }

    @Override
    public String toString() {
        return fieldExpression.toString();
    }
}
