package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
    public String toString() {
        return blockNode.toString();
    }
}
