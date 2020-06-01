package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionNameVarDotFunctionNameNode extends FunctionName {

    private NameNode nameNode;
    private FunctionName functionNameList;

    public FunctionNameVarDotFunctionNameNode(NameNode nameNode, FunctionName functionNameList) {
        this.nameNode = nameNode;
        if (nameNode != null)
            nameNode.setParent(this);
        
        this.functionNameList = functionNameList;
        if (functionNameList != null)
            functionNameList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (nameNode != null)
            nameNode.accept(visitor);
        
        if (functionNameList != null)
            functionNameList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        
        if (nameNode != null)
            nameNode.traverseTopDown(visitor);
        
        if (functionNameList != null)
            functionNameList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameNode != null)
            nameNode.traverseBottomUp(visitor);
        
        if (functionNameList != null)
            functionNameList.traverseBottomUp(visitor);
        
        accept(visitor);
    }
}
