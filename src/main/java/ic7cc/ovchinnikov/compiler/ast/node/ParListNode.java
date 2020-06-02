package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParListNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(localName = "NameList")
    private NameListNode nameListNode;
    @JacksonXmlProperty(isAttribute = true, localName = "varPar")
    private Boolean varParList;

    public ParListNode(NameListNode nameListNode, Boolean varParList) {
        this.nameListNode = nameListNode;
        this.varParList = varParList;
    }

    @Override
    public String toString() {
        String string = nameListNode.toString();
        if (varParList)
            string += ",...";

        return string;
    }
}
