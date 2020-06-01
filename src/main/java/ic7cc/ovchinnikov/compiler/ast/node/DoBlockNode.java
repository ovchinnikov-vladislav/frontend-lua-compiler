package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoBlockNode extends Statement {

    @JacksonXmlProperty(localName = "BlockNode")
    private BlockNode blockNode;

    public DoBlockNode(BlockNode blockNode) {
        this.blockNode = blockNode;
        if (blockNode != null) blockNode.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (blockNode != null) blockNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (blockNode != null)
            blockNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (blockNode != null)
            blockNode.traverseBottomUp(visitor);
        accept(visitor);
    }
}
