package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class NameListSerializer extends StdSerializer<NameList> {

    public NameListSerializer() {
        this(null);
    }

    public NameListSerializer(Class<NameList> t) {
        super(t);
    }

    @Override
    public void serialize(NameList value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Name v : value.getNameList()) {
            gen.writeObjectField("Name", v);
        }
        gen.writeEndObject();
    }
}
