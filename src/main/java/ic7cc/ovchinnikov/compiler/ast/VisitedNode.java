package ic7cc.ovchinnikov.compiler.ast;

public interface VisitedNode {

    void accept(Visitor visitor);
    void childrenAccept(Visitor visitor);
    void traverseBottomUp(Visitor visitor);
    void traverseTopDown(Visitor visitor);

}
