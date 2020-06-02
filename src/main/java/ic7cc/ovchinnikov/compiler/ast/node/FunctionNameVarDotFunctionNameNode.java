package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
    public String toString() {
        return nameNode + "." + functionName;
    }
}
