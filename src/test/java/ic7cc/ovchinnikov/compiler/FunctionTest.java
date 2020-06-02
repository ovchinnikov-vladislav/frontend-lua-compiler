package ic7cc.ovchinnikov.compiler;

import ic7cc.ovchinnikov.compiler.ast.node.BlockNode;
import ic7cc.ovchinnikov.compiler.lexer.Lexer;
import ic7cc.ovchinnikov.compiler.parser.Parser;
import ic7cc.ovchinnikov.compiler.util.XmlSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;

public class FunctionTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void functionApplySimpleTest() throws Exception {
        File file = Path.of("lua/functions/functions_apply_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/functions/xml/functions_apply_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/functions/xml/functions_apply_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/functions/xml/expected_functions_apply_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void functionDefineSimpleTest() throws Exception {
        File file = Path.of("lua/functions/functions_define_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/functions/xml/functions_define_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/functions/xml/functions_define_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/functions/xml/expected_functions_define_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void functionMultipleParamsTest() throws Exception {
        File file = Path.of("lua/functions/functions_multiple_params.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/functions/xml/functions_multiple_params.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/functions/xml/functions_multiple_params.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/functions/xml/expected_functions_multiple_params.xml").toFile());

        Assert.assertEquals(expected, result);
    }
}
