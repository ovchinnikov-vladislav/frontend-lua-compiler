package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrefixExpressionFunctionCallNode extends PrefixExpression {

    private FunctionCall call;

    public PrefixExpressionFunctionCallNode(FunctionCall call) {
        this.call = call;

        if (call != null)
            call.setParent(this);
    }

    @Override
    public String toString() {
        return call.toString();
    }
}
