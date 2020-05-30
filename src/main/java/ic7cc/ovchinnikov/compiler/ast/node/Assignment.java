package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Assignment extends Stat {

    private VarList varList;
    private ExpList expList;

    public Assignment(VarList varList, ExpList expList) {
        this.varList = varList;
        if (varList != null)
            varList.setParent(this);

        this.expList = expList;
        if (expList != null)
            expList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (varList != null)
            varList.accept(visitor);

        if (expList != null)
            expList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (varList != null)
            varList.traverseTopDown(visitor);

        if (expList != null)
            expList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (varList != null)
            varList.traverseBottomUp(visitor);

        if (expList != null)
            expList.traverseBottomUp(visitor);

        accept(visitor);
    }

}
