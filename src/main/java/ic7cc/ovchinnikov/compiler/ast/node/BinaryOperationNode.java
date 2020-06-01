package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Operation;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
}
