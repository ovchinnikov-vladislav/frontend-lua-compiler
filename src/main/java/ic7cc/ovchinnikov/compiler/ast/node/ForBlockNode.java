package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForBlockNode extends Statement {

    private String ident;
    private Expression startExpression;
    private Expression endExpression;
    private Expression stepExpression;
    private BlockNode blockNode;

    public ForBlockNode(String ident, Expression startExpression, Expression endExpression, Expression stepExpression, BlockNode blockNode) {
        this.ident = ident;
        this.startExpression = startExpression;
        if (startExpression != null)
            startExpression.setParent(this);
        this.endExpression = endExpression;
        if (endExpression != null)
            endExpression.setParent(this);
        this.stepExpression = stepExpression;
        if (stepExpression != null)
            stepExpression.setParent(this);
        this.blockNode = blockNode;
        if (blockNode != null)
            blockNode.setParent(this);
    }

    @Override
    public String toString() {
        return "for " + ident + " = " + startExpression + ", " + endExpression + ", " + stepExpression + " do \n" + blockNode +"\nend";
    }
}
