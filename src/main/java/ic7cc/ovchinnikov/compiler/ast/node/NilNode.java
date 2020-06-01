package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class NilNode extends Expression {

    public NilNode() {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    @Override
    public String toString() {
        return "nil";
    }
}
