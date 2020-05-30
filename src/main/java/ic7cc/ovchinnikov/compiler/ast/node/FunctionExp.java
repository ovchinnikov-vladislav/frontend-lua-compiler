package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionExp extends Exp {

    private NameList args;
    @JacksonXmlProperty(isAttribute = true)
    private boolean varArgs;
    private Block block;

    public FunctionExp (NameList args, boolean varArgs, Block block) {
        this.args = args;
        if (args != null) args.setParent(this);
        this.varArgs = varArgs;
        this.block = block;
        if (block != null) block.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (args != null)
            args.accept(visitor);

        if (block != null)
            block.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (args != null)
            args.traverseTopDown(visitor);

        if (block != null)
            block.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (args != null)
            args.traverseBottomUp(visitor);

        if (block != null)
            block.traverseBottomUp(visitor);

        accept(visitor);
    }
}
