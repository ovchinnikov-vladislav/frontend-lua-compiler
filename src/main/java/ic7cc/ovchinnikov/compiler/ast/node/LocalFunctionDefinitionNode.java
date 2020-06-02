package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalFunctionDefinitionNode extends Statement {

    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(localName = "NameList")
    private NameListNode args;
    @JacksonXmlProperty(isAttribute = true)
    private boolean varArgs;
    @JacksonXmlProperty(localName = "BlockNode")
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
    public String toString() {
        if (varArgs)
            return "local function " + name + "(" + args + ",...)\n" + blockNode;
        else
            return "local function " + name + "(" + args + ")\n" + blockNode;
    }
}
