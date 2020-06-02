package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalNode extends Statement {

    @JacksonXmlProperty(localName = "NameList")
    private NameListNode nameListNode;
    @JacksonXmlProperty(localName = "ExpList")
    private ExpressionListNode expressionListNode;

    public LocalNode(NameListNode nameListNode, ExpressionListNode expressionListNode) {
        this.nameListNode = nameListNode;
        if (nameListNode != null)
            nameListNode.setParent(this);

        this.expressionListNode = expressionListNode;
        if (expressionListNode != null)
            expressionListNode.setParent(this);
    }

    @Override
    public String toString() {
        return "local" + nameListNode + " = " + expressionListNode;
    }
}
