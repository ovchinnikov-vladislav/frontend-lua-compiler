package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class TableConstructorNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(localName = "FieldList")
    public FieldListNode fieldListNode;

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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (fieldListNode != null)
            fieldListNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (fieldListNode != null)
            fieldListNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (fieldListNode != null)
            fieldListNode.traverseBottomUp(visitor);

        accept(visitor);
    }
    
}
