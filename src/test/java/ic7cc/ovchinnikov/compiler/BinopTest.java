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

public class BinopTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void binopTestAnd() throws Exception {
        File file = Path.of("lua/binop/binop_and.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_and.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_and.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_and.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopConcat() throws Exception {
        File file = Path.of("lua/binop/binop_concat.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_concat.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_concat.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_concat.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestDiv() throws Exception {
        File file = Path.of("lua/binop/binop_div.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_div.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_div.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_div.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestEqual() throws Exception {
        File file = Path.of("lua/binop/binop_eq.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_equal.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_equal.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_equal.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestMoreEq() throws Exception {
        File file = Path.of("lua/binop/binop_ge.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_more_equal.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_more_equal.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_more_equal.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestMore() throws Exception {
        File file = Path.of("lua/binop/binop_gt.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_more.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_more.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_more.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestLess() throws Exception {
        File file = Path.of("lua/binop/binop_lt.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_less.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_less.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_less.xml").toFile());

        Assert.assertEquals(expected, result);
    }


    @Test
    public void binopTestLessEqual() throws Exception {
        File file = Path.of("lua/binop/binop_le.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_less_equal.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_less_equal.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_less_equal.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestMod() throws Exception {
        File file = Path.of("lua/binop/binop_mod.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_mod.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_mod.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_mod.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestNotEqual() throws Exception {
        File file = Path.of("lua/binop/binop_neq.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_not_equal.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_not_equal.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_not_equal.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestOr() throws Exception {
        File file = Path.of("lua/binop/binop_or.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_or.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_or.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_or.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestAdd() throws Exception {
        File file = Path.of("lua/binop/binop_plus.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_add.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_add.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_add.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestPow() throws Exception {
        File file = Path.of("lua/binop/binop_pow.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_pow.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_pow.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_pow.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestSub() throws Exception {
        File file = Path.of("lua/binop/binop_sub.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_sub.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_sub.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_sub.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopTestTimes() throws Exception {
        File file = Path.of("lua/binop/binop_times.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_times.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_times.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_times.xml").toFile());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void binopsTest() throws Exception {
        File file = Path.of("lua/operators/binops_simple.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/binop/xml/binop_simple.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/binop/xml/binop_simple.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/binop/xml/expected_binop_simple.xml").toFile());

        Assert.assertEquals(expected, result);

        ExpressionDataTypeTableBuilder builder = new ExpressionDataTypeTableBuilder();
        builder.analyze(block);

        Map<String, ExpressionDataTypeTableBuilder.Type> map = builder.getTypeMap();

        Assert.assertEquals(ExpressionDataTypeTableBuilder.Type.BOOLEAN, map.get("p"));
    }
}
