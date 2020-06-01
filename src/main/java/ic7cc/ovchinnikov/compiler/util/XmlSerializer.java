package ic7cc.ovchinnikov.compiler.util;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ic7cc.ovchinnikov.compiler.ast.xml.*;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.File;
import java.io.IOException;

public class XmlSerializer {

    public void save(BlockNode blockNode, File file) throws IOException {
        XmlMapper mapper = new XmlMapper();

        SimpleModule module = new SimpleModule();
      //  module.addSerializer(AssignmentNode.class, new AssignmentSerializer());
       // module.addSerializer(BlockNode.class, new BlockSerializer());
        module.addSerializer(StatementListNode.class, new StatListSerializer());
        module.addSerializer(ExpressionListNode.class, new ExpListSerializer());
        module.addSerializer(NameListNode.class, new NameListSerializer());
        module.addSerializer(FieldListNode.class, new FieldListSerializer());
        module.addSerializer(VariableListNode.class, new VarListSerializer());
        module.addSerializer(BinaryOperationNode.class, new BinopSerializer());
        module.addSerializer(PrefixExpressionVariableNode.class, new PrefixExpVarSerializer());
        module.addSerializer(PrefixExpressionNode.class, new PreExpSerializer());
        module.addSerializer(ForBlockNode.class, new ForBlockSerializer());
        module.addSerializer(IfThenElseBlockNode.class, new IfThenElseBlockSerializer());
        module.addSerializer(RepeatUntilNode.class, new RepeatUntilSerializer());
        module.addSerializer(VariableTabIndexNode.class, new VarTableIndexSerializer());
        module.addSerializer(FieldNameExpressionNode.class, new FieldNameExpressionSerializer());
        module.addSerializer(FieldLeftRightExpressionNode.class, new FieldLeftRightExpressionSerializer());
        mapper.registerModule(module);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        mapper.writeValue(file, blockNode);
    }

}
