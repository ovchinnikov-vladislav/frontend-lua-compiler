package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class For extends Stat {

    @Override
    public ASTNode getParent() {
        return null;
    }

    @Override
    public void setParent(ASTNode parent) {

    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void childrenAccept(Visitor visitor) {

    }

    @Override
    public void traverseBottomUp(Visitor visitor) {

    }

    @Override
    public void traverseTopDown(Visitor visitor) {

    }
}
