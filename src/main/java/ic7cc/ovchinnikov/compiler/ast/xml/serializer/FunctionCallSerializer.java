package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallNode;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallStatementNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FunctionCallSerializer extends StdSerializer<FunctionCallNode> {

    public FunctionCallSerializer() {
        this(null);
    }

    public FunctionCallSerializer(Class<FunctionCallNode> t) {
        super(t);
    }

    @Override
    public void serialize(FunctionCallNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializePreExp(value.getPreExp(), gen);
        gen.writeObjectField("ExpList", value.getExpressionListNode());

        gen.writeEndObject();
    }
}
