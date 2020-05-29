package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Block extends ASTNode {

    private ASTNode parent;
    private StatList statList;
    private RetStat retStat;

    public Block(StatList statList, RetStat retStat) {
        this.statList = statList;
        if (statList != null)
            statList.setParent(this);
        this.retStat = retStat;
        if (retStat != null)
            retStat.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

        if (statList != null)
            statList.accept(visitor);

        if (retStat != null)
            retStat.accept(visitor);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (statList != null)
            statList.accept(visitor);

        if (retStat != null)
            retStat.accept(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (statList != null)
            statList.traverseBottomUp(visitor);

        if (retStat != null)
            retStat.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (statList != null)
            statList.traverseTopDown(visitor);

        if (retStat != null)
            retStat.traverseTopDown(visitor);
    }

}
