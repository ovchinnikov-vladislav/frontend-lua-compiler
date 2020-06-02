package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForInBlockNode extends Statement {

    @JacksonXmlProperty(localName = "NameList")
    private NameListNode nameListNode;
    @JacksonXmlProperty(localName = "ExpList")
    private ExpressionListNode expressionListNode;
    @JacksonXmlProperty(localName = "BlockNode")
    private BlockNode blockNode;

    public ForInBlockNode(NameListNode nameListNode, ExpressionListNode expressionListNode, BlockNode blockNode) {
        this.nameListNode = nameListNode;
        if (nameListNode != null)
            nameListNode.setParent(this);
        this.expressionListNode = expressionListNode;
        if (expressionListNode != null)
            expressionListNode.setParent(this);
        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);
    }

    @Override
    public String toString() {
        return "for " + nameListNode + " in " + expressionListNode + " do \n" + blockNode + "\nend";
    }
}
