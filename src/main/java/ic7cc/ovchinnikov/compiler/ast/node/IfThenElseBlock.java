package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class IfThenElseBlock extends Stat {

    public Exp ifExp;
    public Block thenBlock;
    public Block elseBlock;

    public IfThenElseBlock(Exp ifExp, Block thenBlock, Block elseBlock) {
        this.ifExp = ifExp;
        if (ifExp != null)
            ifExp.setParent(this);

        this.thenBlock = thenBlock;
        if (thenBlock != null)
            thenBlock.setParent(this);

        this.elseBlock = elseBlock;
        if (elseBlock != null)
            elseBlock.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (ifExp != null)
            ifExp.accept(visitor);

        if (thenBlock != null)
            thenBlock.accept(visitor);

        if (elseBlock != null)
            elseBlock.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (ifExp != null)
            ifExp.traverseTopDown(visitor);

        if (thenBlock != null)
            thenBlock.traverseTopDown(visitor);

        if (elseBlock != null)
            elseBlock.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (ifExp != null)
            ifExp.traverseBottomUp(visitor);

        if (thenBlock != null)
            thenBlock.traverseBottomUp(visitor);

        if (elseBlock != null)
            elseBlock.traverseBottomUp(visitor);

        accept(visitor);
    }
}
