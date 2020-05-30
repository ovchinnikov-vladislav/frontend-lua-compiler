package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldNameExp extends Field {

    @JacksonXmlProperty(isAttribute = true)
    private String ident;
    private Exp exp;

    public FieldNameExp(String ident, Exp exp) {
        this.ident = ident;
        this.exp = exp;
        if (exp != null)
            exp.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (exp != null)
            exp.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (exp != null)
            exp.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (exp != null)
            exp.traverseBottomUp(visitor);

        accept(visitor);
    }
}
