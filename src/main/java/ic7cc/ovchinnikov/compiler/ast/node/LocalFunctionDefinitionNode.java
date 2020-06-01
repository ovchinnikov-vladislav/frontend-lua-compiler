package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalFunctionDefinitionNode extends Statement {

    @JacksonXmlProperty(isAttribute = true)
    private String name;
    private NameListNode args;
    @JacksonXmlProperty(isAttribute = true)
    private boolean varArgs;
    private BlockNode blockNode;

    public LocalFunctionDefinitionNode(String name, NameListNode args, boolean varArgs, BlockNode blockNode) {
        this.name = name;
        this.args = args;
        if (args != null)
            args.setParent(this);

        this.varArgs = varArgs;

        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);
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
}
