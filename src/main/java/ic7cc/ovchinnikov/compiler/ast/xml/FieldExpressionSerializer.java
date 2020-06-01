package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.FieldExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.node.FieldLeftRightExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FieldExpressionSerializer extends StdSerializer<FieldExpressionNode> {

    public FieldExpressionSerializer() {
        this(null);
    }

    public FieldExpressionSerializer(Class<FieldExpressionNode> t) {
        super(t);
    }

    @Override
    public void serialize(FieldExpressionNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializeExpression(value.getFieldExpression(), gen);

        gen.writeEndObject();
    }
}
