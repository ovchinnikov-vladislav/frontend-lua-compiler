package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.Field;
import ic7cc.ovchinnikov.compiler.ast.node.FieldListNode;
import ic7cc.ovchinnikov.compiler.ast.node.FieldNameExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FieldNameExpressionSerializer extends StdSerializer<FieldNameExpressionNode> {

    public FieldNameExpressionSerializer() {
        this(null);
    }

    public FieldNameExpressionSerializer(Class<FieldNameExpressionNode> t) {
        super(t);
    }

    @Override
    public void serialize(FieldNameExpressionNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        ToXmlGenerator xmlGenerator = (ToXmlGenerator) gen;

        xmlGenerator.setNextIsAttribute(true);
        xmlGenerator.writeStringField("ident", value.getIdent());

        SerializeDefinedType.serializeExpression(value.getExpression(), gen);

        gen.writeEndObject();
    }
}
