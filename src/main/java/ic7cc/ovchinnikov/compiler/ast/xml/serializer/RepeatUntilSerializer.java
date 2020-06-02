package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class RepeatUntilSerializer extends StdSerializer<RepeatUntilNode> {

    public RepeatUntilSerializer() {
        this(null);
    }

    public RepeatUntilSerializer(Class<RepeatUntilNode> t) {
        super(t);
    }

    @Override
    public void serialize(RepeatUntilNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeObjectField("BlockNode", value.getBlockNode());

        Expression v = value.getExpression();
        SerializeDefinedType.serializeExpression(v, gen);

        gen.writeEndObject();
    }
}
