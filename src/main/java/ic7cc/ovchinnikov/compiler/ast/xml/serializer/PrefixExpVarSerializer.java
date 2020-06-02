package ic7cc.ovchinnikov.compiler.ast.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.PrefixExpressionVariableNode;
import ic7cc.ovchinnikov.compiler.ast.node.VariableNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class PrefixExpVarSerializer extends StdSerializer<PrefixExpressionVariableNode> {

    public PrefixExpVarSerializer() {
        this(null);
    }

    public PrefixExpVarSerializer(Class<PrefixExpressionVariableNode> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(PrefixExpressionVariableNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        SerializeDefinedType.serializeVariable(value.getVariable(), gen);
        gen.writeEndObject();
    }
}
