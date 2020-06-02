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

public class CommentTest {

    private XmlSerializer xmlSerializer;

    @Before
    public void init() {
        xmlSerializer = new XmlSerializer();
    }

    @Test
    public void commentsSimpleTest() throws Exception {
        File file = Path.of("lua/comments/comment.lua").toFile();

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        BlockNode block = (BlockNode) parser.parse().value;

        xmlSerializer.save(block, Path.of("result/comments/xml/comment.xml").toFile());

        HashMap<String, Object> result = xmlSerializer.read(Path.of("result/comments/xml/comment.xml").toFile());
        HashMap<String, Object> expected = xmlSerializer.read(Path.of("expected/comments/xml/expected_comment.xml").toFile());

        Assert.assertEquals(expected, result);
    }

}
