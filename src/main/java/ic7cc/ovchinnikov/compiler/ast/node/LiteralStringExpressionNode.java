package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiteralStringExpressionNode extends Expression {

    @JacksonXmlProperty(isAttribute = true)
    private String string;

    public LiteralStringExpressionNode(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
