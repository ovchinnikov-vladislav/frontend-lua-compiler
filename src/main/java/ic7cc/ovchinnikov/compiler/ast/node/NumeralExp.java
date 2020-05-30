package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumeralExp extends Exp {

    @JacksonXmlProperty(isAttribute = true)
    private Number number;
    @JacksonXmlProperty(isAttribute = true)
    private Type type;

    public NumeralExp(Number number, Type type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public enum Type {
        INTEGER,
        DOUBLE
    }
}
