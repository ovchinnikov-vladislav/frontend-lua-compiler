package ic7cc.ovchinnikov.compiler.token;

import ic7cc.ovchinnikov.compiler.ast.ASTNode;
import ic7cc.ovchinnikov.compiler.parser.location.LocationFactory;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

public class CustomSymbolFactory extends ComplexSymbolFactory {

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        if (value instanceof ASTNode) {
            ((ASTNode) value).setStart(LocationFactory.from(left));
            if (right.left != -1 && right.right != -1)
                ((ASTNode) value).setEnd(LocationFactory.from(right));
        }

        Symbol s = new Symbol(id, left, right, value);
        return s;
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        return super.newSymbol(name, id);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return super.newSymbol(name, id, left, right);
    }

}