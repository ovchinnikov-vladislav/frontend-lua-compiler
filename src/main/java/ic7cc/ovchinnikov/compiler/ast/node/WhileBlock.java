package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class WhileBlock extends Stat {

    public Exp exp;
    public Block block;

    public WhileBlock(Exp exp, Block block) {
        this.exp = exp;
        if (exp != null) exp.setParent(this);
        this.block = block;
        if (block != null) block.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (exp != null)
            exp.accept(visitor);

        if (block != null)
            block.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (exp != null) exp.traverseTopDown(visitor);
        if (block != null) block.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (exp != null) exp.traverseBottomUp(visitor);
        if (block != null) block.traverseBottomUp(visitor);
        accept(visitor);
    }
}
