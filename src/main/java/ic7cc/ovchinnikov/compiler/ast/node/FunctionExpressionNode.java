package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (args != null)
            args.accept(visitor);

        if (blockNode != null)
            blockNode.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (args != null)
            args.traverseTopDown(visitor);

        if (blockNode != null)
            blockNode.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (args != null)
            args.traverseBottomUp(visitor);

        if (blockNode != null)
            blockNode.traverseBottomUp(visitor);

        accept(visitor);
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

        return "function (" + args + ") " + blockNode;
    }
}
