package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.Variable;
import ic7cc.ovchinnikov.compiler.ast.node.VariableListNode;
import ic7cc.ovchinnikov.compiler.ast.node.VariableTabIndexNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class VarTableIndexSerializer extends StdSerializer<VariableTabIndexNode> {

    public VarTableIndexSerializer() {
        this(null);
    }

    public VarTableIndexSerializer(Class<VariableTabIndexNode> t) {
        super(t);
    }

    @Override
    public void serialize(VariableTabIndexNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        SerializeDefinedType.serializePreExp(value.getPreExp(), gen);
        SerializeDefinedType.serializeExpression(value.getIndexExpression(), gen);
        gen.writeEndObject();
    }
}
