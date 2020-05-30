package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class PrefixExpVar extends PrefixExp {

    public Var var;

    public PrefixExpVar(Var var) {
        this.var = var;

        if (var != null)
            var.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (var != null)
            var.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (var != null)
            var.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (var != null)
            var.traverseBottomUp(visitor);

        accept(visitor);
    }
}
