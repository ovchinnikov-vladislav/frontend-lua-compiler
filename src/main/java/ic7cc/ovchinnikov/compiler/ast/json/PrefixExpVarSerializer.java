package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.Assignment;
import ic7cc.ovchinnikov.compiler.ast.node.PrefixExpVar;
import ic7cc.ovchinnikov.compiler.ast.node.Variable;

import java.io.IOException;

public class PrefixExpVarSerializer extends StdSerializer<PrefixExpVar> {

    public PrefixExpVarSerializer() {
        this(null);
    }

    public PrefixExpVarSerializer(Class<PrefixExpVar> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(PrefixExpVar value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        if (value.getVar() instanceof Variable)
            gen.writeObjectField("Variable", value.getVar());
        gen.writeEndObject();
    }
}
