package ic7cc.ovchinnikov.compiler.parser.location;

import java_cup.runtime.Symbol;

public class LocationFactory {

    public static Location from(Symbol symbol) {
        if (symbol != null) {
            return new Location(symbol.left, symbol.right);
        }
        return null;
    }

}
