package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class FuncBody extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    private final ParList parList;
    private final Block block;

    public FuncBody(ParList parList, Block block) {
        this.parList = parList;
        this.block = block;
    }

    @Override
    public ASTNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    @Override
    public void accept(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    public NameList getArgs() {
        return parList.getNameList();
    }

    public boolean getVarArgs() {
        return parList.getVarParList();
    }

    public Block getBlock() {
        return block;
    }

}
