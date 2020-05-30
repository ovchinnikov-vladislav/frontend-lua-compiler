package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class StatListSerializer extends StdSerializer<StatList> {

    public StatListSerializer() {
        this(null);
    }

    public StatListSerializer(Class<StatList> t) {
        super(t);
    }

    @Override
    public void serialize(StatList value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Stat v : value.getStatList()) {
            if (v instanceof Assignment)
                gen.writeObjectField("Assignment", v);
            else if (v instanceof IfThenElseBlock)
                gen.writeObjectField("IfThenElseBlock", v);
            else if (v instanceof Break)
                gen.writeObjectField("Break", v);
            else if (v instanceof DoBlock)
                gen.writeObjectField("DoBlock", v);
            else if (v instanceof ForBlock)
                gen.writeObjectField("ForBlock", v);
            else if (v instanceof ForInBlock)
                gen.writeObjectField("ForInBlock", v);
            else if (v instanceof FuncCallStatement)
                gen.writeObjectField("FuncCallStatement", v);
            else if (v instanceof Local)
                gen.writeObjectField("Local", v);
            else if (v instanceof LocalFunctionDef)
                gen.writeObjectField("LocalFunctionDef", v);
            else if (v instanceof RepeatUntil)
                gen.writeObjectField("RepeatUntil", v);
        }
        gen.writeEndObject();
    }
}
