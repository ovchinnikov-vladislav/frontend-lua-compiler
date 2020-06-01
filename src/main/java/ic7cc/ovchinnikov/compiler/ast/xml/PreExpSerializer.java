package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class PreExpSerializer extends StdSerializer<PrefixExpressionNode> {

    public PreExpSerializer() {
        this(null);
    }

    public PreExpSerializer(Class<PrefixExpressionNode> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(PrefixExpressionNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        SerializeDefinedType.serializePreExp(value.getPreExp(), gen);
        gen.writeEndObject();
    }
}
