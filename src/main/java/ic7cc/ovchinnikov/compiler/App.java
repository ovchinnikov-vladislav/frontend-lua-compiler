/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ic7cc.ovchinnikov.compiler;

import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.lexer.Lexer;
import ic7cc.ovchinnikov.compiler.parser.Parser;
import ic7cc.ovchinnikov.compiler.semantic.ExpressionDataTypeTableBuilder;
import ic7cc.ovchinnikov.compiler.util.XmlSerializer;

import java.io.File;
import java.io.FileReader;

public class App {

    public static void main(String[] args) throws Exception {
//        Parser parser = new Parser(new Lexer(new FileReader("lua/calculator.lua")));
//        parser.parse();
        Parser parser = new Parser(new Lexer(new FileReader("lua/testlocal.lua")));
        try {
            Object object = parser.parse().value;

            if (object instanceof BlockNode) {
                ExpressionDataTypeTableBuilder builder = new ExpressionDataTypeTableBuilder();
                builder.analyze((BlockNode) object);

                XmlSerializer serializer = new XmlSerializer();

                serializer.save((BlockNode) object, new File("result/xml/testlocal.xml"));
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
