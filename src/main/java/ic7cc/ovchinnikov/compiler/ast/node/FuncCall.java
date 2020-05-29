package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class FuncCall extends FunctionCall {

    public PrefixExp preExp;
    public ExpList expList;

    public FuncCall(PrefixExp preExp, ExpList expList) {
        this.preExp = preExp;
        if (preExp != null) preExp.setParent(this);
        this.expList = expList;
        if (expList != null) expList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (preExp != null)
            preExp.accept(visitor);

        if (expList != null)
            expList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (preExp != null)
            preExp.traverseTopDown(visitor);

        if (expList != null)
            expList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (preExp != null)
            preExp.traverseBottomUp(visitor);

        if (expList != null)
            expList.traverseBottomUp(visitor);

        accept(visitor);
    }
}
