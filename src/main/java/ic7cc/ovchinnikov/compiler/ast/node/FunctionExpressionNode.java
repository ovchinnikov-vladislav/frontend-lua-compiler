package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FunctionExpressionNode extends Expression {

    @JacksonXmlProperty(localName = "NameList")
    private NameListNode args;
    @JacksonXmlProperty(isAttribute = true)
    private boolean varArgs;
    @JacksonXmlProperty(localName = "Block")
    private BlockNode blockNode;

    public FunctionExpressionNode(NameListNode args, boolean varArgs, BlockNode blockNode) {
        this.args = args;
        if (args != null) args.setParent(this);
        this.varArgs = varArgs;
        this.blockNode = blockNode;
        if (blockNode != null) blockNode.setParent(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        List<NameNode> nameNodes = args.getNameNodeList();
        for (NameNode nameNode : nameNodes) {
            builder.append(nameNode);
            builder.append(",");
        }
        String args = builder.toString();
        if (varArgs)
            args = builder.append("...").toString();
        else if (!args.trim().isEmpty() && args.charAt(args.length() - 1) == ',')
            args = args.substring(0, args.length() - 1);

        return "function (" + args + ") " + blockNode + "; end";
    }
}
