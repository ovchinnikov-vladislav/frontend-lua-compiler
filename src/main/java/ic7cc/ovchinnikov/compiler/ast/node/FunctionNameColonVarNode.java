package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionNameColonVarNode extends FunctionName {

    @JacksonXmlProperty(localName = "SelfFunctionName")
    private NameNode selfFuncNameNode;
    @JacksonXmlProperty(localName = "FunctionName")
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
    public String toString() {
        return selfFuncNameNode + ":" + funcNameNode;
    }
}
