package ic7cc.ovchinnikov.compiler.ast.xml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.io.IOException;

public class StatListSerializer extends StdSerializer<StatementListNode> {

    public StatListSerializer() {
        this(null);
    }

    public StatListSerializer(Class<StatementListNode> t) {
        super(t);
    }

    @Override
    public void serialize(StatementListNode value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Statement v : value.getStatementList()) {
            SerializeDefinedType.serializeStatement(v, gen);
        }
        gen.writeEndObject();
    }
}
