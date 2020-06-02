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

public class BlocksTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void forSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/for_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/for_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/for_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_for_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void foreachSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/foreach_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/foreach_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/foreach_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_foreach_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void forInLisaTest() throws Exception {
        File file = Path.of("lua/control_structures/forin_lisa.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/forin_lisa.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/forin_lisa.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_forin_lisa.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void ifElseIfSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/if_else_if_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/if_else_if_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/if_else_if_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_if_else_if_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void ifElseSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/if_else_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/if_else_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/if_else_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_if_else_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void ifSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/if_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/if_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/if_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_if_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void repeatUntilSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/repeat_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/repeat_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/repeat_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_repeat_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void whileSimpleTest() throws Exception {
        File file = Path.of("lua/control_structures/while_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/blocks/xml/while_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/blocks/xml/while_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/blocks/xml/expected_while_simple.xml").toFile());

        Assert.assertEquals(expected, result);
    }
}
