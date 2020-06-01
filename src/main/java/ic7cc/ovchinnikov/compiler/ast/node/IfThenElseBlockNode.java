package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IfThenElseBlockNode extends Statement {

    private Expression ifExpression;
    private BlockNode thenBlockNode;
    private BlockNode elseBlockNode;

    public IfThenElseBlockNode(Expression ifExpression, BlockNode thenBlockNode, BlockNode elseBlockNode) {
        this.ifExpression = ifExpression;
        if (ifExpression != null)
            ifExpression.setParent(this);

        this.thenBlockNode = thenBlockNode;
        if (thenBlockNode != null)
            thenBlockNode.setParent(this);

        this.elseBlockNode = elseBlockNode;
        if (elseBlockNode != null)
            elseBlockNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (ifExpression != null)
            ifExpression.accept(visitor);

        if (thenBlockNode != null)
            thenBlockNode.accept(visitor);

        if (elseBlockNode != null)
            elseBlockNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (ifExpression != null)
            ifExpression.traverseTopDown(visitor);

        if (thenBlockNode != null)
            thenBlockNode.traverseTopDown(visitor);

        if (elseBlockNode != null)
            elseBlockNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (ifExpression != null)
            ifExpression.traverseBottomUp(visitor);

        if (thenBlockNode != null)
            thenBlockNode.traverseBottomUp(visitor);

        if (elseBlockNode != null)
            elseBlockNode.traverseBottomUp(visitor);

        accept(visitor);
    }
}
