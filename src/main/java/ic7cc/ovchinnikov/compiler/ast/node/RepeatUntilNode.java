package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepeatUntilNode extends Statement {

    private BlockNode blockNode;
    private Expression expression;

    public RepeatUntilNode(BlockNode blockNode, Expression expression) {
        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);

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
        if (blockNode != null)
            blockNode.accept(visitor);

        if (expression != null)
            expression.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (blockNode != null)
            blockNode.traverseTopDown(visitor);

        if (expression != null)
            expression.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (blockNode != null)
            blockNode.traverseBottomUp(visitor);

        if (expression != null)
            expression.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public String toString() {
        return "repeat\n" + blockNode + "\nuntil " + expression;
    }
}
