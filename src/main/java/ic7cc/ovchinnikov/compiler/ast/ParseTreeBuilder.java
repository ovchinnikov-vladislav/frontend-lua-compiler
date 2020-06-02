package ic7cc.ovchinnikov.compiler.ast;

import ic7cc.ovchinnikov.compiler.ast.node.BlockNode;
import ic7cc.ovchinnikov.compiler.lexer.Lexer;
import ic7cc.ovchinnikov.compiler.parser.Parser;
import ic7cc.ovchinnikov.compiler.semantic.ExpressionDataTypeTableBuilder;
import ic7cc.ovchinnikov.compiler.util.XmlSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParseTreeBuilder {

    public BlockNode builder(String filename, String toFileXmlName) throws Exception {
        Parser parser = new Parser(new Lexer(new FileReader(filename)));
        Object object = parser.parse().value;

        if (object instanceof BlockNode) {

            XmlSerializer serializer = new XmlSerializer();

            serializer.save((BlockNode) object, new File(toFileXmlName));
            return (BlockNode) object;
        }
        return null;
    }

}
