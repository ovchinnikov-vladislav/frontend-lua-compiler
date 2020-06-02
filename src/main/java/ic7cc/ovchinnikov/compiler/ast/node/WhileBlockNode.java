package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhileBlockNode extends Statement {

    private Expression expression;
    private BlockNode blockNode;

    public WhileBlockNode(Expression expression, BlockNode blockNode) {
        this.expression = expression;
        if (expression != null) expression.setParent(this);
        this.blockNode = blockNode;
        if (blockNode != null) blockNode.setParent(this);
    }

    @Override
    public String toString() {
        return "while " + expression + " do \n" + blockNode + "\nend";
    }
}
