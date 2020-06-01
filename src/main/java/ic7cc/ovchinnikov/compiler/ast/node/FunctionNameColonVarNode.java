package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionNameColonVarNode extends FunctionName {

    private NameNode selfFuncNameNode;
    private NameNode funcNameNode;

    public FunctionNameColonVarNode(NameNode selfFuncNameNode, NameNode funcNameNode) {
        this.selfFuncNameNode = selfFuncNameNode;
        if (selfFuncNameNode != null)
            selfFuncNameNode.setParent(this);

        this.funcNameNode = funcNameNode;
        if (funcNameNode != null)
            funcNameNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (selfFuncNameNode != null)
            selfFuncNameNode.accept(visitor);

        if (funcNameNode != null)
            funcNameNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (selfFuncNameNode != null)
            selfFuncNameNode.traverseTopDown(visitor);

        if (funcNameNode != null)
            funcNameNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (selfFuncNameNode != null)
            selfFuncNameNode.traverseBottomUp(visitor);

        if (funcNameNode != null)
            funcNameNode.traverseBottomUp(visitor);

        accept(visitor);
    }
}
