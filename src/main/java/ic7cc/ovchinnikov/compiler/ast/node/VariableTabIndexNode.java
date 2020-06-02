package ic7cc.ovchinnikov.compiler.ast.node;

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
    public String toString() {
        return preExp + " [" + indexExpression + "]";
    }
}
