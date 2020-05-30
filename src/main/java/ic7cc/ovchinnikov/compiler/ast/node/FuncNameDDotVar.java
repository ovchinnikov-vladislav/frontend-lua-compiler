package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncNameDDotVar extends FuncName {

    private Name selfFuncName;
    private Name funcName;

    public FuncNameDDotVar(Name selfFuncName, Name funcName) {
        this.selfFuncName = selfFuncName;
        if (selfFuncName != null)
            selfFuncName.setParent(this);

        this.funcName = funcName;
        if (funcName != null)
            funcName.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (selfFuncName != null)
            selfFuncName.accept(visitor);

        if (funcName != null)
            funcName.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (selfFuncName != null)
            selfFuncName.traverseTopDown(visitor);

        if (funcName != null)
            funcName.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (selfFuncName != null)
            selfFuncName.traverseBottomUp(visitor);

        if (funcName != null)
            funcName.traverseBottomUp(visitor);

        accept(visitor);
    }
}
