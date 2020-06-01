package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableTabIndexNode extends Variable {

    private PrefixExpression preExp;
    private Expression indexExpression;

    public VariableTabIndexNode(PrefixExpression preExp, Expression indexExpression) {
        this.preExp = preExp;
        if (preExp != null)
            preExp.setParent(this);

        this.indexExpression = indexExpression;
        if (indexExpression != null)
            indexExpression.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (preExp != null)
            preExp.accept(visitor);

        if (indexExpression != null)
            indexExpression.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (preExp != null)
            preExp.traverseTopDown(visitor);

        if (indexExpression != null)
            indexExpression.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (preExp != null)
            preExp.traverseBottomUp(visitor);

        if (indexExpression != null)
            indexExpression.traverseBottomUp(visitor);

        accept(visitor);
    }
}
