package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.FieldExpressionNode;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallStatementNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FunctionCallStatementSerializer extends StdSerializer<FunctionCallStatementNode> {

    public FunctionCallStatementSerializer() {
        this(null);
    }

    public FunctionCallStatementSerializer(Class<FunctionCallStatementNode> t) {
        super(t);
    }

    @Override
    public void serialize(FunctionCallStatementNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializeFunctionCall(value.getCall(), gen);

        gen.writeEndObject();
    }
}
