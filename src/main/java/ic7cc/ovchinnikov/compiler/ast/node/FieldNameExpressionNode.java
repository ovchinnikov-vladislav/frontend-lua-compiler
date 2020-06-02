package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldNameExpressionNode extends Field {

    @JacksonXmlProperty(isAttribute = true)
    private String ident;
    private Expression expression;

    public FieldNameExpressionNode(String ident, Expression expression) {
        this.ident = ident;
        this.expression = expression;
        if (expression != null)
            expression.setParent(this);
    }

    @Override
    public String toString() {
        return ident + " = " + expression;
    }
}
