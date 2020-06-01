package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class NameListSerializer extends StdSerializer<NameListNode> {

    public NameListSerializer() {
        this(null);
    }

    public NameListSerializer(Class<NameListNode> t) {
        super(t);
    }

    @Override
    public void serialize(NameListNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (NameNode v : value.getNameNodeList()) {
            gen.writeObjectField("Name", v);
        }
        gen.writeEndObject();
    }
}
