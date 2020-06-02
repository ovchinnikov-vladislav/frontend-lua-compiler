package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class IfThenElseBlockSerializer extends StdSerializer<IfThenElseBlockNode> {

    public IfThenElseBlockSerializer() {
        this(null);
    }

    public IfThenElseBlockSerializer(Class<IfThenElseBlockNode> t) {
        super(t);
    }

    @Override
    public void serialize(IfThenElseBlockNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        Expression v = value.getIfExpression();
        SerializeDefinedType.serializeExpression(v, gen);

        gen.writeObjectField("BlockNode", value.getThenBlockNode());
        gen.writeObjectField("BlockNode", value.getElseBlockNode());
        gen.writeEndObject();
    }
}
