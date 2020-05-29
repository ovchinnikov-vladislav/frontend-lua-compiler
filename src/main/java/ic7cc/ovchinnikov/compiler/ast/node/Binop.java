package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Operation;
import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class Binop extends Exp {

    public Exp leftExp;
    public Operation op;
    public Exp rightExp;

    public Binop(Exp leftExp, Operation op, Exp rightExp) {
        this.leftExp = leftExp;

        if (leftExp != null)
            leftExp.setParent(this);

        this.op = op;
        this.rightExp = rightExp;

        if (rightExp != null)
            rightExp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (leftExp != null)
            leftExp.accept(visitor);
        if (rightExp != null)
            rightExp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (leftExp != null)
            leftExp.traverseTopDown(visitor);

        if (rightExp != null)
            rightExp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (leftExp != null)
            leftExp.traverseBottomUp(visitor);

        if (rightExp != null)
            rightExp.traverseBottomUp(visitor);

        accept(visitor);
    }
}
