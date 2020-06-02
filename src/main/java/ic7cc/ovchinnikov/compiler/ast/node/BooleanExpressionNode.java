package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanExpressionNode extends Expression {

    @JacksonXmlProperty(isAttribute = true)
    private boolean value;

    public BooleanExpressionNode(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
