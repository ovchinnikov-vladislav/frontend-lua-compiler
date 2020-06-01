package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class BinopSerializer extends StdSerializer<BinaryOperationNode> {

    public BinopSerializer() {
        this(null);
    }

    public BinopSerializer(Class<BinaryOperationNode> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(BinaryOperationNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject(value.getOperation());
        final ToXmlGenerator xmlGenerator = (ToXmlGenerator) gen;

        xmlGenerator.setNextIsAttribute(true);
        gen.writeStringField("operation", value.getOperation().name());

        Expression leftExpression = value.getLeftExpression();
        SerializeDefinedType.serializeExpression(leftExpression, gen);

        Expression rightExpression = value.getRightExpression();
        SerializeDefinedType.serializeExpression(rightExpression, gen);

        gen.writeEndObject();
    }
}
