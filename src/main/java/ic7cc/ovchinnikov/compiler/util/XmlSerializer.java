package ic7cc.ovchinnikov.compiler.util;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.serializer.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
        module.addSerializer(UnaryOperationNode.class, new UnopSerializer());
        module.addSerializer(WhileBlockNode.class, new WhileSerializer());
        module.addSerializer(PrefixExpressionLPExpressionRPNode.class, new PrefixExpressionLPExpressionRPSerializer());
        module.addSerializer(PrefixExpressionVariableNode.class, new PrefixExpVarSerializer());
        module.addSerializer(PrefixExpressionNode.class, new PreExpSerializer());
        module.addSerializer(ForBlockNode.class, new ForBlockSerializer());
        module.addSerializer(IfThenElseBlockNode.class, new IfThenElseBlockSerializer());
        module.addSerializer(RepeatUntilNode.class, new RepeatUntilSerializer());
        module.addSerializer(VariableTabIndexNode.class, new VarTableIndexSerializer());
        module.addSerializer(FieldNameExpressionNode.class, new FieldNameExpressionSerializer());
        module.addSerializer(FieldLeftRightExpressionNode.class, new FieldLeftRightExpressionSerializer());
        module.addSerializer(FieldExpressionNode.class, new FieldExpressionSerializer());
        module.addSerializer(FunctionCallStatementNode.class, new FunctionCallStatementSerializer());
        module.addSerializer(FunctionCallNode.class, new FunctionCallSerializer());
        module.addSerializer(FunctionCallSelfNode.class, new FunctionCallSelfSerializer());
        module.addSerializer(PrefixExpressionFunctionCallNode.class, new PrefixExpressionFunctionCallSerializer());
        module.addSerializer(FunctionDefinitionNode.class, new FunctionDefinitionSerializer());
        mapper.registerModule(module);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(file, blockNode);
    }

    public HashMap<String, Object> read(File file) throws IOException {
        XmlMapper mapper = new XmlMapper();

        return mapper.readValue(file, HashMap.class);
    }
}
