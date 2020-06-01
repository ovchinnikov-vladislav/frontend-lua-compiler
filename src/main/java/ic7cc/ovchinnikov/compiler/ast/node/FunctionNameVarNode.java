package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (nameNode != null)
            nameNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (nameNode != null)
            nameNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameNode != null)
            nameNode.traverseBottomUp(visitor);

        accept(visitor);
    }
}
