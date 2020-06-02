package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallNode;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallSelfNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FunctionCallSelfSerializer extends StdSerializer<FunctionCallSelfNode> {

    public FunctionCallSelfSerializer() {
        this(null);
    }

    public FunctionCallSelfSerializer(Class<FunctionCallSelfNode> t) {
        super(t);
    }

    @Override
    public void serialize(FunctionCallSelfNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        ToXmlGenerator xmlGenerator = (ToXmlGenerator) gen;

        xmlGenerator.setNextIsAttribute(true);
        xmlGenerator.writeStringField("name", value.getName());

        SerializeDefinedType.serializePreExp(value.getPreExp(), gen);
        gen.writeObjectField("ExpList", value.getExpressionListNode());

        gen.writeEndObject();
    }
}
