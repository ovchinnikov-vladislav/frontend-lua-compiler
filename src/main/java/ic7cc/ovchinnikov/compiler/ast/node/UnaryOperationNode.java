package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnaryOperationNode extends Expression {

    private Operation operation;
    private Expression expression;

    public UnaryOperationNode(Operation operation, Expression expression) {
        this.operation = operation;
        this.expression = expression;
        if (expression != null) expression.setParent(this);
    }

    @Override
    public String toString() {
        return operation + expression.toString();
    }
}
