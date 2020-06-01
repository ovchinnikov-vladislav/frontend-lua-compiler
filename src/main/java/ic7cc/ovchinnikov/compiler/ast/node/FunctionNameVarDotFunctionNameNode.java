package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionNameVarDotFunctionNameNode extends FunctionName {

    @JacksonXmlProperty(localName = "Name")
    private NameNode nameNode;
    @JacksonXmlProperty(localName = "FunctionName")
    private FunctionName functionName;

    public FunctionNameVarDotFunctionNameNode(NameNode nameNode, FunctionName functionName) {
        this.nameNode = nameNode;
        if (nameNode != null)
            nameNode.setParent(this);
        
        this.functionName = functionName;
        if (functionName != null)
            functionName.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (nameNode != null)
            nameNode.accept(visitor);
        
        if (functionName != null)
            functionName.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        
        if (nameNode != null)
            nameNode.traverseTopDown(visitor);
        
        if (functionName != null)
            functionName.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameNode != null)
            nameNode.traverseBottomUp(visitor);
        
        if (functionName != null)
            functionName.traverseBottomUp(visitor);
        
        accept(visitor);
    }

    @Override
    public String toString() {
        return nameNode + "." + functionName;
    }
}
