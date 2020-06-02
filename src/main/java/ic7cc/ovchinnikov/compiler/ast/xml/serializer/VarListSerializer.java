package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class VarListSerializer extends StdSerializer<VariableListNode> {

    public VarListSerializer() {
        this(null);
    }

    public VarListSerializer(Class<VariableListNode> t) {
        super(t);
    }

    @Override
    public void serialize(VariableListNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Variable v : value.getVariableList()) {
            SerializeDefinedType.serializeVariable(v, gen);
        }
        gen.writeEndObject();
    }
}
