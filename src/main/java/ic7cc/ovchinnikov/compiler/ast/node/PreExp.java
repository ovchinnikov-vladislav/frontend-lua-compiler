package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class PreExp extends Exp {

    public PrefixExp preExp;

    public PreExp (PrefixExp preExp) {
        this.preExp = preExp;

        if (preExp != null)
            preExp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (preExp != null)
            preExp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (preExp != null)
            preExp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (preExp != null)
            preExp.traverseBottomUp(visitor);

        accept(visitor);
    }
}
