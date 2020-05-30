package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForBlock extends Stat {

    private String ident;
    private Exp startExp;
    private Exp endExp;
    private Exp stepExp;
    private Block block;

    public ForBlock(String ident, Exp startExp, Exp endExp, Exp stepExp, Block block) {
        this.ident = ident;
        this.startExp = startExp;
        if (startExp != null)
            startExp.setParent(this);
        this.endExp = endExp;
        if (endExp != null)
            endExp.setParent(this);
        this.stepExp = stepExp;
        if (stepExp != null)
            stepExp.setParent(this);
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
        if (startExp != null)
            startExp.accept(visitor);
        if (endExp != null)
            endExp.accept(visitor);
        if (stepExp != null)
            stepExp.accept(visitor);
        if (block != null)
            block.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (startExp != null)
            startExp.traverseTopDown(visitor);
        if (endExp != null)
            endExp.traverseTopDown(visitor);
        if (stepExp != null)
            stepExp.traverseTopDown(visitor);
        if (block != null)
            block.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (startExp != null)
            startExp.traverseBottomUp(visitor);
        if (endExp != null)
            endExp.traverseBottomUp(visitor);
        if (stepExp != null)
            stepExp.traverseBottomUp(visitor);
        if (block != null)
            block.traverseBottomUp(visitor);

        accept(visitor);
    }
}
