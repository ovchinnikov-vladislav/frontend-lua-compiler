package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FieldListSerializer extends StdSerializer<FieldListNode> {

    public FieldListSerializer() {
        this(null);
    }

    public FieldListSerializer(Class<FieldListNode> t) {
        super(t);
    }

    @Override
    public void serialize(FieldListNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Field v : value.getFieldList()) {
            SerializeDefinedType.serializeField(v, gen);
        }
        gen.writeEndObject();
    }
}
