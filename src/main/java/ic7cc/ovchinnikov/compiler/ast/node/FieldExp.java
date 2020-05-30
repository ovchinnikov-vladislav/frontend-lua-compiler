package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldExp extends Field {

    private Exp fieldExp;

    public FieldExp(Exp fieldExp) {
        this.fieldExp = fieldExp;
        if (fieldExp != null)
            fieldExp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (fieldExp != null)
            fieldExp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (fieldExp != null)
            fieldExp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (fieldExp != null)
            fieldExp.traverseBottomUp(visitor);
        accept(visitor);
    }
}
