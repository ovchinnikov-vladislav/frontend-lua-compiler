package ic7cc.ovchinnikov.compiler.ast.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ic7cc.ovchinnikov.compiler.ast.node.Block;
import ic7cc.ovchinnikov.compiler.ast.node.Return;

import java.io.IOException;

public class BlockSerializer extends StdSerializer<Block> {

    public BlockSerializer() {
        this(null);
    }

    public BlockSerializer(Class<Block> t) {
        super(t);
    }

    @Override
    public void serialize(Block value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("StatList", value.getStatList());
        if (value.getRetStat() instanceof Return) {
            gen.writeObjectField("Return", value.getRetStat());
        }
        gen.writeEndObject();
    }
}
