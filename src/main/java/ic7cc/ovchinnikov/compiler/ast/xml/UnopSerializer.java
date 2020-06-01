package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.BinaryOperationNode;
import ic7cc.ovchinnikov.compiler.ast.node.Expression;
import ic7cc.ovchinnikov.compiler.ast.node.UnaryOperationNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class UnopSerializer extends StdSerializer<UnaryOperationNode> {

    public UnopSerializer() {
        this(null);
    }

    public UnopSerializer(Class<UnaryOperationNode> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(UnaryOperationNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject(value.getOperation());
        final ToXmlGenerator xmlGenerator = (ToXmlGenerator) gen;

        xmlGenerator.setNextIsAttribute(true);
        gen.writeStringField("operation", value.getOperation().name());

        SerializeDefinedType.serializeExpression(value.getExpression(), gen);

        gen.writeEndObject();
    }
}
