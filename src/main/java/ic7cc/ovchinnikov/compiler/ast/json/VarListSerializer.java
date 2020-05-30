package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class VarListSerializer extends StdSerializer<VarList> {

    public VarListSerializer() {
        this(null);
    }

    public VarListSerializer(Class<VarList> t) {
        super(t);
    }

    @Override
    public void serialize(VarList value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        for (Var v : value.getVarList()) {
            if (v instanceof VarTabIndex)
                gen.writeObjectField("VarTabIndex", v);
            else if (v instanceof Variable)
                gen.writeObjectField("Variable", v);
        }
        gen.writeEndObject();
    }
}
