package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableNode extends Variable {

    @JacksonXmlProperty(isAttribute = true)
    private String var;

    public VariableNode(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }
}
