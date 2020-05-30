package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class ExpListSerializer extends StdSerializer<ExpList> {

    public ExpListSerializer() {
        this(null);
    }

    public ExpListSerializer(Class<ExpList> t) {
        super(t);
    }

    @Override
    public void serialize(ExpList value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Exp v : value.getExpList()) {
            if (v instanceof BooleanExp)
                gen.writeObjectField("BooleanExp", v);
            else if (v instanceof Binop)
                gen.writeObjectField("BinaryOperation", v);
            else if (v instanceof Dots)
                gen.writeObjectField("Dots", v);
            else if (v instanceof FunctionExp)
                gen.writeObjectField("FunctionExp", v);
            else if (v instanceof LiteralStringExp)
                gen.writeObjectField("LiteralStringExp", v);
            else if (v instanceof Nil)
                gen.writeObjectField("Nil", v);
            else if (v instanceof NumeralExp)
                gen.writeObjectField("NumeralExp", v);
            else if (v instanceof PreExp)
                gen.writeObjectField("PreExp", v);
            else if (v instanceof TableConstructorExp)
                gen.writeObjectField("TableConstructor", v);
            else if (v instanceof Unop)
                gen.writeObjectField("UnaryOperation", v);
        }
        gen.writeEndObject();
    }
}
