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

public class ReturnTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void returnTest() throws Exception {
        File file = Path.of("lua/return/laststat_return.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/return/xml/laststat_return.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/return/xml/laststat_return.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/return/xml/expected_laststat_return.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void returnEmptyTest() throws Exception {
        File file = Path.of("lua/return/laststat_return_empty.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/return/xml/laststat_return_empty.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/return/xml/laststat_return_empty.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/return/xml/expected_laststat_return_empty.xml").toFile());

        Assert.assertEquals(expected, result);
    }

}
