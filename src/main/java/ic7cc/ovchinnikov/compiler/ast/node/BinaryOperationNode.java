package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryOperationNode extends Expression {

    private Expression leftExpression;
    @JacksonXmlProperty(isAttribute = true)
    private Operation operation;
    private Expression rightExpression;

    public BinaryOperationNode(Expression leftExpression, Operation operation, Expression rightExpression) {
        this.leftExpression = leftExpression;

        if (leftExpression != null)
            leftExpression.setParent(this);

        this.operation = operation;
        this.rightExpression = rightExpression;

        if (rightExpression != null)
            rightExpression.setParent(this);
    }

    @Override
    public String toString() {
        return leftExpression + " " +  operation + " " + rightExpression;
    }
}
