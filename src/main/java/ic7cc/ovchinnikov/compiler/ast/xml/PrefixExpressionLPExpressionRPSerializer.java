package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.PrefixExpressionFunctionCallNode;
import ic7cc.ovchinnikov.compiler.ast.node.PrefixExpressionLPExpressionRPNode;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class PrefixExpressionLPExpressionRPSerializer extends StdSerializer<PrefixExpressionLPExpressionRPNode> {

    public PrefixExpressionLPExpressionRPSerializer() {
        this(null);
    }

    public PrefixExpressionLPExpressionRPSerializer(Class<PrefixExpressionLPExpressionRPNode> t) {
        super(t);
    }

    @Override
    public void serialize(PrefixExpressionLPExpressionRPNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        SerializeDefinedType.serializeExpression(value.getExpression(), gen);

        gen.writeEndObject();
    }
}
