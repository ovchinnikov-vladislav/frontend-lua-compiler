package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumeralExpressionNode extends Expression {

    @JacksonXmlProperty(isAttribute = true)
    private Number number;
    @JacksonXmlProperty(isAttribute = true)
    private Type type;

    public NumeralExpressionNode(Number number, Type type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return number.toString();
    }

    public enum Type {
        INTEGER,
        DOUBLE
    }
}
