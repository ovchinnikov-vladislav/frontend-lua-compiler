package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForInBlock extends Stat {

    private NameList nameList;
    private ExpList expList;
    private Block block;

    public ForInBlock(NameList nameList, ExpList expList, Block block) {
        this.nameList = nameList;
        if (nameList != null)
            nameList.setParent(this);
        this.expList = expList;
        if (expList != null)
            expList.setParent(this);
        this.block = block;
        if (block != null)
            block.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (nameList != null)
            nameList.accept(visitor);
        if (expList != null)
            expList.accept(visitor);
        if (block != null)
            block.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (nameList != null)
            nameList.traverseTopDown(visitor);
        if (expList != null)
            expList.traverseTopDown(visitor);
        if (block != null)
            block.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (nameList != null)
            nameList.traverseBottomUp(visitor);
        if (expList != null)
            expList.traverseBottomUp(visitor);
        if (block != null)
            block.traverseBottomUp(visitor);

        accept(visitor);
    }
}
