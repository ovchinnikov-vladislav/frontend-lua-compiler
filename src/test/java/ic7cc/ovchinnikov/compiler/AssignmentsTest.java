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

public class AssignmentsTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void assignSimpleTest() throws Exception {
        File file = Path.of("lua/assignments/assignments_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/assignments/xml/assign_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/assignments/xml/assign_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/assignments/xml/expected_assign_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void assignMultipleTest() throws Exception {
        File file = Path.of("lua/assignments/assignments_multiple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/assignments/xml/assign_multiple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/assignments/xml/assign_multiple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/assignments/xml/expected_assign_multiple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

}
