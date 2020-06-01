package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.Expression;
import ic7cc.ovchinnikov.compiler.ast.node.RepeatUntilNode;
import ic7cc.ovchinnikov.compiler.ast.node.WhileBlockNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class WhileSerializer extends StdSerializer<WhileBlockNode> {

    public WhileSerializer() {
        this(null);
    }

    public WhileSerializer(Class<WhileBlockNode> t) {
        super(t);
    }

    @Override
    public void serialize(WhileBlockNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        Expression v = value.getExpression();
        SerializeDefinedType.serializeExpression(v, gen);

        gen.writeObjectField("BlockNode", value.getBlockNode());

        gen.writeEndObject();
    }
}
