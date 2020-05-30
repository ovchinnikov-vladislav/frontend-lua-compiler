package ic7cc.ovchinnikov.compiler.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ic7cc.ovchinnikov.compiler.ast.json.*;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class XmlSerializer {

    public void save(Block block, File file) throws IOException {
        XmlMapper mapper = new XmlMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Assignment.class, new AssignmentSerializer());
        module.addSerializer(Block.class, new BlockSerializer());
        module.addSerializer(StatList.class, new StatListSerializer());
        module.addSerializer(ExpList.class, new ExpListSerializer());
        module.addSerializer(NameList.class, new NameListSerializer());
        module.addSerializer(FieldList.class, new FieldListSerializer());
        module.addSerializer(VarList.class, new VarListSerializer());
        module.addSerializer(Binop.class, new BinopSerializer());
        module.addSerializer(PrefixExpVar.class, new PrefixExpVarSerializer());
        module.addSerializer(PreExp.class, new PreExpSerializer());
        mapper.registerModule(module);

        mapper.writeValue(file, block);
    }

}
