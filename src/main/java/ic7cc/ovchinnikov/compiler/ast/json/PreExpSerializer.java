package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class PreExpSerializer extends StdSerializer<PreExp> {

    public PreExpSerializer() {
        this(null);
    }

    public PreExpSerializer(Class<PreExp> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(PreExp value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        if (value.getPreExp() instanceof PrefixExpVar)
            gen.writeObjectField("PrefixExpVar", value.getPreExp());
        else if (value.getPreExp() instanceof PrefixExpExp)
            gen.writeObjectField("PrefixExpExp", value.getPreExp());
        else if (value.getPreExp() instanceof PrefixExpFuncCall)
            gen.writeObjectField("PrefixExpFuncCall", value.getPreExp());
        gen.writeEndObject();
    }
}
