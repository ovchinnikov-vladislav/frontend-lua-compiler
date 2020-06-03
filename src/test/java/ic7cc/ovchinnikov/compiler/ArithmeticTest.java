package ic7cc.ovchinnikov.compiler;

import ic7cc.ovchinnikov.compiler.ast.node.BlockNode;
import ic7cc.ovchinnikov.compiler.lexer.Lexer;
import ic7cc.ovchinnikov.compiler.parser.Parser;
import ic7cc.ovchinnikov.compiler.semantic.ExpressionDataTypeTableBuilder;
import ic7cc.ovchinnikov.compiler.util.XmlSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ArithmeticTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void arithSimpleTest() throws Exception {
        File file = Path.of("lua/arith/arith_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/arithmetic/xml/arith_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/arithmetic/xml/arith_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/arithmetic/xml/expected_arith_simple.xml").toFile());

        Assert.assertEquals(expected, result);

        ExpressionDataTypeTableBuilder builder = new ExpressionDataTypeTableBuilder();
        builder.analyze(block);

        Map<String, ExpressionDataTypeTableBuilder.Type> typeMap = builder.getTypeMap();

        Assert.assertEquals(ExpressionDataTypeTableBuilder.Type.DOUBLE, typeMap.get("a"));
    }

}
