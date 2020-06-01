package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.FieldLeftRightExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.node.FieldNameExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FieldLeftRightExpressionSerializer extends StdSerializer<FieldLeftRightExpressionNode> {

    public FieldLeftRightExpressionSerializer() {
        this(null);
    }

    public FieldLeftRightExpressionSerializer(Class<FieldLeftRightExpressionNode> t) {
        super(t);
    }

    @Override
    public void serialize(FieldLeftRightExpressionNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializeExpression(value.getLeftExpression(), gen);
        SerializeDefinedType.serializeExpression(value.getRightExpression(), gen);

        gen.writeEndObject();
    }
}
