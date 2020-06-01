package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForBlockNode extends Statement {

    private String ident;
    private Expression startExpression;
    private Expression endExpression;
    private Expression stepExpression;
    private BlockNode blockNode;

    public ForBlockNode(String ident, Expression startExpression, Expression endExpression, Expression stepExpression, BlockNode blockNode) {
        this.ident = ident;
        this.startExpression = startExpression;
        if (startExpression != null)
            startExpression.setParent(this);
        this.endExpression = endExpression;
        if (endExpression != null)
            endExpression.setParent(this);
        this.stepExpression = stepExpression;
        if (stepExpression != null)
            stepExpression.setParent(this);
        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (startExpression != null)
            startExpression.accept(visitor);
        if (endExpression != null)
            endExpression.accept(visitor);
        if (stepExpression != null)
            stepExpression.accept(visitor);
        if (blockNode != null)
            blockNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (startExpression != null)
            startExpression.traverseTopDown(visitor);
        if (endExpression != null)
            endExpression.traverseTopDown(visitor);
        if (stepExpression != null)
            stepExpression.traverseTopDown(visitor);
        if (blockNode != null)
            blockNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (startExpression != null)
            startExpression.traverseBottomUp(visitor);
        if (endExpression != null)
            endExpression.traverseBottomUp(visitor);
        if (stepExpression != null)
            stepExpression.traverseBottomUp(visitor);
        if (blockNode != null)
            blockNode.traverseBottomUp(visitor);

        accept(visitor);
    }
}
