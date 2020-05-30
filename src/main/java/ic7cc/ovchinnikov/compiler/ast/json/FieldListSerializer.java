package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class FieldListSerializer extends StdSerializer<FieldList> {

    public FieldListSerializer() {
        this(null);
    }

    public FieldListSerializer(Class<FieldList> t) {
        super(t);
    }

    @Override
    public void serialize(FieldList value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Field v : value.getFieldList()) {
            if (v instanceof FieldNameExp)
                gen.writeObjectField("FieldNameExp", v);
            else if (v instanceof FieldExp)
                gen.writeObjectField("FieldExp", v);
            else if (v instanceof FieldLeftRightExp)
                gen.writeObjectField("FieldLeftRightExp", v);
        }
        gen.writeEndObject();
    }
}
