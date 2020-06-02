package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class ExpListSerializer extends StdSerializer<ExpressionListNode> {

    public ExpListSerializer() {
        this(null);
    }

    public ExpListSerializer(Class<ExpressionListNode> t) {
        super(t);
    }

    @Override
    public void serialize(ExpressionListNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Expression v : value.getExpressionList()) {
            SerializeDefinedType.serializeExpression(v, gen);
        }
        gen.writeEndObject();
    }
}
