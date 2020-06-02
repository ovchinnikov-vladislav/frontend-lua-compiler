package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionBodyNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(localName = "ParList")
    private final ParListNode parListNode;
    @JacksonXmlProperty(localName = "BlockNode")
    private final BlockNode blockNode;


    public FunctionBodyNode(ParListNode parListNode, BlockNode blockNode) {
        this.parListNode = parListNode;
        this.blockNode = blockNode;
    }

    @Override
    public ASTNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }

    @Override
    public String toString() {
        return "("+parListNode+") " + blockNode + " end";
    }
}
