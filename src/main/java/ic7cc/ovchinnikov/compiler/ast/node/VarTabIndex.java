package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VarTabIndex extends Var {

    private PrefixExp preExp;
    private Exp indexExp;

    public VarTabIndex (PrefixExp preExp, Exp indexExp) {
        this.preExp = preExp;
        if (preExp != null)
            preExp.setParent(this);

        this.indexExp = indexExp;
        if (indexExp != null)
            indexExp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (preExp != null)
            preExp.accept(visitor);

        if (indexExp != null)
            indexExp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (preExp != null)
            preExp.traverseTopDown(visitor);

        if (indexExp != null)
            indexExp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (preExp != null)
            preExp.traverseBottomUp(visitor);

        if (indexExp != null)
            indexExp.traverseBottomUp(visitor);

        accept(visitor);
    }
}
