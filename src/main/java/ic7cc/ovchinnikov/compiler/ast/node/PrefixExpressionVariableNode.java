package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrefixExpressionVariableNode extends PrefixExpression {

    private Variable variable;

    public PrefixExpressionVariableNode(Variable variable) {
        this.variable = variable;

        if (variable != null)
            variable.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (variable != null)
            variable.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (variable != null)
            variable.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (variable != null)
            variable.traverseBottomUp(visitor);

        accept(visitor);
    }
}
