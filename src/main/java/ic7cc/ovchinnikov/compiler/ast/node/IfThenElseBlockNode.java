package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IfThenElseBlockNode extends Statement {

    private Expression ifExpression;
    private BlockNode thenBlockNode;
    private BlockNode elseBlockNode;

    public IfThenElseBlockNode(Expression ifExpression, BlockNode thenBlockNode, BlockNode elseBlockNode) {
        this.ifExpression = ifExpression;
        if (ifExpression != null)
            ifExpression.setParent(this);

        this.thenBlockNode = thenBlockNode;
        if (thenBlockNode != null)
            thenBlockNode.setParent(this);

        this.elseBlockNode = elseBlockNode;
        if (elseBlockNode != null)
            elseBlockNode.setParent(this);
    }

    @Override
    public String toString() {
        return "if " + ifExpression + " then\n" + thenBlockNode + "else\n" + elseBlockNode;
    }
}
