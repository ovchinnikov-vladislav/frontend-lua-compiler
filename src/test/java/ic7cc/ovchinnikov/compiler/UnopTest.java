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

public class UnopTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void unopLengthTest() throws Exception {
        File file = Path.of("lua/unop/unop_length.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/unop/xml/unop_length.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/unop/xml/unop_length.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/unop/xml/expected_unop_length.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void unopNotConcat() throws Exception {
        File file = Path.of("lua/unop/unop_not.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/unop/xml/unop_not.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/unop/xml/unop_not.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/unop/xml/expected_unop_not.xml").toFile());

        Assert.assertEquals(expected, result);
    }

}
