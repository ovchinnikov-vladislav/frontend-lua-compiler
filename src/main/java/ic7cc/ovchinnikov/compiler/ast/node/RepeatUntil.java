package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class RepeatUntil extends Stat {

    public Block block;
    public Exp exp;

    public RepeatUntil (Block block, Exp exp) {
        this.block = block;
        if (block != null)
            block.setParent(this);

        this.exp = exp;
        if (exp != null)
            exp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (block != null)
            block.accept(visitor);

        if (exp != null)
            exp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (block != null)
            block.traverseTopDown(visitor);

        if (exp != null)
            exp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (block != null)
            block.traverseBottomUp(visitor);

        if (exp != null)
            exp.traverseBottomUp(visitor);

        accept(visitor);
    }
}