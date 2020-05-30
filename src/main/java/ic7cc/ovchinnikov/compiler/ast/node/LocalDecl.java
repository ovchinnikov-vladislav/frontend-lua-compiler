package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class LocalDecl extends Stat {

    public NameList nameList;
    public ExpList expList;

    public LocalDecl(NameList nameList, ExpList expList) {
        this.nameList = nameList;
        if (nameList != null)
            nameList.setParent(this);

        this.expList = expList;
        if (expList != null)
            expList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (nameList != null)
            nameList.accept(visitor);

        if (expList != null)
            expList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (nameList != null)
            nameList.traverseTopDown(visitor);

        if (expList != null)
            expList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameList != null)
            nameList.traverseBottomUp(visitor);

        if (expList != null)
            expList.traverseBottomUp(visitor);

        accept(visitor);
    }
}
