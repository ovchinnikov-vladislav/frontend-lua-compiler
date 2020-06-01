package ic7cc.ovchinnikov.compiler.ast.xml.util;

import com.fasterxml.jackson.core.JsonGenerator;
import ic7cc.ovchinnikov.compiler.ast.node.*;

import java.io.IOException;

public class SerializeDefinedType {

    public static void serializeExpression(Expression v, JsonGenerator gen) throws IOException {
        if (v instanceof BooleanExpressionNode)
            gen.writeObjectField("BooleanExpression", v);
        else if (v instanceof BinaryOperationNode)
            gen.writeObjectField("BinaryOperation", v);
        else if (v instanceof DotDotDotNode)
            gen.writeObjectField("DotDotDot", v);
        else if (v instanceof FunctionExpressionNode)
            gen.writeObjectField("FunctionExpression", v);
        else if (v instanceof LiteralStringExpressionNode)
            gen.writeObjectField("LiteralStringExpression", v);
        else if (v instanceof NilNode)
            gen.writeObjectField("Nil", v);
        else if (v instanceof NumeralExpressionNode)
            gen.writeObjectField("NumeralExpression", v);
        else if (v instanceof PrefixExpressionNode)
            gen.writeObjectField("PrefixExpression", v);
        else if (v instanceof TableConstructorExpressionNode)
            gen.writeObjectField("TableConstructor", ((TableConstructorExpressionNode) v).tableCons);
        else if (v instanceof UnaryOperationNode)
            gen.writeObjectField("UnaryOperation", v);
    }

    public static void serializeField(Field v, JsonGenerator gen) throws IOException {
        if (v instanceof FieldNameExpressionNode)
            gen.writeObjectField("FieldNameExpression", v);
        else if (v instanceof FieldExpressionNode)
            gen.writeObjectField("FieldExpression", v);
        else if (v instanceof FieldLeftRightExpressionNode)
            gen.writeObjectField("FieldLeftRightExpression", v);
    }

    public static void serializePreExp(PrefixExpression v, JsonGenerator gen) throws IOException {
        if (v instanceof PrefixExpressionVariableNode)
            gen.writeObjectField("PrefixExpressionVariable", v);
        else if (v instanceof PrefixExpressionExpressionNode)
            gen.writeObjectField("PrefixExpressionExpression", v);
        else if (v instanceof PrefixExpressionFunctionCallNode)
            gen.writeObjectField("PrefixExpressionFunctionCall", v);
    }

    public static void serializeVariable(Variable v, JsonGenerator gen) throws IOException {
        if (v instanceof VariableTabIndexNode)
            gen.writeObjectField("VariableTableIndex", v);
        else if (v instanceof VariableNode)
            gen.writeObjectField("Variable", v);
    }

    public static void serializeStatement(Statement v, JsonGenerator gen) throws IOException {
        if (v instanceof AssignmentNode)
            gen.writeObjectField("Assignment", v);
        else if (v instanceof IfThenElseBlockNode)
            gen.writeObjectField("IfThenElseBlock", v);
        else if (v instanceof BreakNode)
            gen.writeObjectField("Break", v);
        else if (v instanceof DoBlockNode)
            gen.writeObjectField("DoBlock", v);
        else if (v instanceof ForBlockNode)
            gen.writeObjectField("ForBlock", v);
        else if (v instanceof ForInBlockNode)
            gen.writeObjectField("ForInBlock", v);
        else if (v instanceof FunctionCallStatementNode)
            gen.writeObjectField("FuncCallStatement", v);
        else if (v instanceof LocalNode)
            gen.writeObjectField("Local", v);
        else if (v instanceof LocalFunctionDefinitionNode)
            gen.writeObjectField("LocalFunctionDef", v);
        else if (v instanceof RepeatUntilNode)
            gen.writeObjectField("RepeatUntil", v);
    }
}
