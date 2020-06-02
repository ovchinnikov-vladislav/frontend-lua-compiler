package ic7cc.ovchinnikov.compiler.ast.node;

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
    public String toString() {
        return variable.toString();
    }
}
