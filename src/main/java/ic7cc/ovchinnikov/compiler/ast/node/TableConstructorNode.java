package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableConstructorNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(localName = "FieldList")
    private FieldListNode fieldListNode;

    public TableConstructorNode(FieldListNode fieldListNode) {
        this.fieldListNode = fieldListNode;

        if (fieldListNode != null)
            fieldListNode.setParent(this);
    }

    @Override
    public ASTNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "{" + fieldListNode + "}";
    }
}
