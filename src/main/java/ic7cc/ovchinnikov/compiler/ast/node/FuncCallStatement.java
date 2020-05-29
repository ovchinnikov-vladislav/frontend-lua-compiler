package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class FuncCallStatement extends Stat {

    public FunctionCall call;

    public FuncCallStatement(FunctionCall call) {
        this.call = call;
        if (call != null) call.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (call != null)
            call.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (call != null)
            call.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (call != null)
            call.traverseBottomUp(visitor);

        accept(visitor);
    }
}
