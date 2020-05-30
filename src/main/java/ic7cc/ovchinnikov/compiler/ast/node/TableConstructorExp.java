package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class TableConstructorExp extends Exp {

    public TableConstructor tableCons;

    public TableConstructorExp (TableConstructor tableCons) {
        this.tableCons = tableCons;

        if (tableCons != null)
            tableCons.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (tableCons != null)
            tableCons.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (tableCons != null)
            tableCons.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (tableCons != null)
            tableCons.traverseBottomUp(visitor);

        accept(visitor);
    }
}
