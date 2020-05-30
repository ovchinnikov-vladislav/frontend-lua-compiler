package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForBlock extends Stat {

    private String ident;
    private Exp start;
    private Exp end;
    private Exp step;
    private Block block;

    public ForBlock(String ident, Exp start, Exp end, Exp step, Block block) {
        this.ident = ident;
        this.start = start;
        if (start != null)
            start.setParent(this);
        this.end = end;
        if (end != null)
            end.setParent(this);
        this.step = step;
        if (step != null)
            step.setParent(this);
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
        if (start != null)
            start.accept(visitor);
        if (end != null)
            end.accept(visitor);
        if (step != null)
            step.accept(visitor);
        if (block != null)
            block.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (start != null)
            start.traverseTopDown(visitor);
        if (end != null)
            end.traverseTopDown(visitor);
        if (step != null)
            step.traverseTopDown(visitor);
        if (block != null)
            block.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (start != null)
            start.traverseBottomUp(visitor);
        if (end != null)
            end.traverseBottomUp(visitor);
        if (step != null)
            step.traverseBottomUp(visitor);
        if (block != null)
            block.traverseBottomUp(visitor);

        accept(visitor);
    }
}
