package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (fieldExpression != null)
            fieldExpression.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (fieldExpression != null)
            fieldExpression.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (fieldExpression != null)
            fieldExpression.traverseBottomUp(visitor);
        accept(visitor);
    }
}
