package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class ForBlockSerializer extends StdSerializer<ForBlockNode> {

    public ForBlockSerializer() {
        this(null);
    }

    public ForBlockSerializer(Class<ForBlockNode> t) {
        super(t);
    }

    @Override
    public void serialize(ForBlockNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        ToXmlGenerator xmlGenerator = (ToXmlGenerator) gen;

        xmlGenerator.setNextIsAttribute(true);
        gen.writeStringField("ident", value.getIdent());

        Expression startExpression = value.getStartExpression();
        SerializeDefinedType.serializeExpression(startExpression, gen);

        Expression endExpression = value.getEndExpression();
        SerializeDefinedType.serializeExpression(endExpression, gen);

        Expression stepExpression = value.getStepExpression();
        SerializeDefinedType.serializeExpression(stepExpression, gen);

        gen.writeObjectField("BlockNode", value.getBlockNode());
        gen.writeEndObject();
    }
}
