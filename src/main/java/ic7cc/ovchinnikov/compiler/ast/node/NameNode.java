package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    public NameNode(String name) {
        this.name = name;
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
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    @Override
    public String toString() {
        return name;
    }
}
