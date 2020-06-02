package ic7cc.ovchinnikov.compiler.ast.node;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableConstructorExpressionNode extends Expression {

    private TableConstructorNode tableCons;

    public TableConstructorExpressionNode(TableConstructorNode tableCons) {
        this.tableCons = tableCons;

        if (tableCons != null)
            tableCons.setParent(this);
    }

    @Override
    public String toString() {
        return tableCons.toString();
    }
}
