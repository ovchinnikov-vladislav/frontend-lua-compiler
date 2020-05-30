package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class Return extends RetStat {

    public ExpList expList;

    public Return(ExpList expList) {
        this.expList = expList;
        if (expList != null) expList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (expList != null)
            expList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (expList != null)
            expList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (expList != null)
            expList.traverseBottomUp(visitor);

        accept(visitor);
    }
}
