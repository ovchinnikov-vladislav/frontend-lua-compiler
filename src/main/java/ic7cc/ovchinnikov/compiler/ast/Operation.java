package ic7cc.ovchinnikov.compiler.ast;

public enum Operation {
    POW("^", Type.ARITHMETIC),
    CONCAT("..", Type.STRING),
    LESS("<", Type.RELATION),
    NOT("not", Type.BOOLEAN),
    AND("and", Type.BOOLEAN),
    OR("or", Type.BOOLEAN),
    BAND("&", Type.BITWISE),
    DIV("/", Type.ARITHMETIC),
    FDIV("//", Type.ARITHMETIC),
    BLEFT("<<", Type.BITWISE),
    BXOR("~", Type.BITWISE),
    BNOT("~", Type.BITWISE),
    MOREEQ(">=", Type.RELATION),
    LESSEQ("<=", Type.RELATION),
    EQUAL("==", Type.RELATION),
    MUL("*", Type.ARITHMETIC),
    ADD("+", Type.ARITHMETIC),
    MOD("%", Type.ARITHMETIC),
    BOR("|", Type.BITWISE),
    SUB("-", Type.ARITHMETIC),
    BRIGHT(">>", Type.BITWISE),
    LENGTH("#", Type.STRING),
    MORE(">", Type.RELATION),
    NOTEQ("~=", Type.RELATION),
    UNMINUS("-", Type.ARITHMETIC);

    private final String name;
    private final Type type;

    Operation(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        BOOLEAN,
        RELATION,
        ARITHMETIC,
        BITWISE,
        STRING
    }
}
