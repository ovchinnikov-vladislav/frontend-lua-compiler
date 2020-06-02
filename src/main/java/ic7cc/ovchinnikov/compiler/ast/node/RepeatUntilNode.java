package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepeatUntilNode extends Statement {

    private BlockNode blockNode;
    private Expression expression;

    public RepeatUntilNode(BlockNode blockNode, Expression expression) {
        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);

        this.expression = expression;
        if (expression != null)
            expression.setParent(this);
    }

    @Override
    public String toString() {
        return "repeat\n" + blockNode + "\nuntil " + expression;
    }
}
