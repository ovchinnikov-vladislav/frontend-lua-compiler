package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionCallNode;
import ic7cc.ovchinnikov.compiler.ast.node.FunctionDefinitionNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class FunctionDefinitionSerializer extends StdSerializer<FunctionDefinitionNode> {

    public FunctionDefinitionSerializer() {
        this(null);
    }

    public FunctionDefinitionSerializer(Class<FunctionDefinitionNode> t) {
        super(t);
    }

    @Override
    public void serialize(FunctionDefinitionNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializeFunctionName(value.getFunctionName(), gen);
        gen.writeObjectField("FunctionBody", value.getFunctionBody());

        gen.writeEndObject();
    }
}
