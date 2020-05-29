package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class FuncNameVar extends FuncName {

    public Name name;

    public FuncNameVar(Name name) {
        this.name = name;
        if (name != null) name.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (name != null)
            name.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (name != null)
            name.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (name != null)
            name.traverseBottomUp(visitor);

        accept(visitor);
    }
}
