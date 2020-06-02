package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionNameVarNode extends FunctionName {

    @JacksonXmlProperty(localName = "Name")
    private NameNode nameNode;

    public FunctionNameVarNode(NameNode nameNode) {
        this.nameNode = nameNode;
        if (nameNode != null)
            nameNode.setParent(this);
    }

    @Override
    public String toString() {
        return nameNode.toString();
    }
}
