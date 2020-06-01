package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class WhileBlockNode extends Statement {

    public Expression expression;
    public BlockNode blockNode;

    public WhileBlockNode(Expression expression, BlockNode blockNode) {
        this.expression = expression;
        if (expression != null) expression.setParent(this);
        this.blockNode = blockNode;
        if (blockNode != null) blockNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (expression != null)
            expression.accept(visitor);

        if (blockNode != null)
            blockNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (expression != null) expression.traverseTopDown(visitor);
        if (blockNode != null) blockNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (expression != null) expression.traverseBottomUp(visitor);
        if (blockNode != null) blockNode.traverseBottomUp(visitor);
        accept(visitor);
    }
}
