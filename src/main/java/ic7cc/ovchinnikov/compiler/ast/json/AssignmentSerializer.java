package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.Assignment;

import java.io.IOException;

public class AssignmentSerializer extends StdSerializer<Assignment> {

    public AssignmentSerializer() {
        this(null);
    }

    public AssignmentSerializer(Class<Assignment> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Assignment value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("VarList", value.getVarList());
        gen.writeObjectField("ExpList", value.getExpList());
        gen.writeEndObject();
    }
}
