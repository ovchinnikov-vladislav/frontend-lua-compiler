package ic7cc.ovchinnikov.compiler.semantic;

import ic7cc.ovchinnikov.compiler.ast.Operation;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionDataTypeTableBuilder {

    private Map<String, Type> typeMap = new HashMap<>();

    public void analyze(BlockNode blockNode) {
        blockNode(blockNode);
    }

    private void blockNode(BlockNode block) {
        StatementListNode statementListNode = block.getStatementListNode();
        statementListNode(statementListNode);

        ReturnStatement returnStatement = block.getReturnStatement();
        returnStatement(returnStatement);
    }

    private void statementListNode(StatementListNode statementListNode) {
        List<Statement> statementList = statementListNode.getStatementList();
        for (Statement statement : statementList) {
            statement(statement);
            System.out.println();
        }
    }

    private void returnStatement(ReturnStatement returnStatement) {
        if (returnStatement instanceof ReturnNode) {
            ExpressionListNode expressionListNode = ((ReturnNode) returnStatement).getExpressionListNode();
            List<Expression> expressions = expressionListNode.getExpressionList();
            for (Expression expression : expressions) {
                expression(expression);
            }
        }
    }

    private void statement(Statement statement) {
        if (statement instanceof AssignmentNode)
            assignment((AssignmentNode) statement);
        else if (statement instanceof IfThenElseBlockNode)
            ifThenElse((IfThenElseBlockNode) statement);
        else if (statement instanceof DoBlockNode)
            doBlock((DoBlockNode) statement);
        else if (statement instanceof ForBlockNode)
            forBlock((ForBlockNode) statement);
        else if (statement instanceof ForInBlockNode)
            forInBlock((ForInBlockNode) statement);
        else if (statement instanceof FunctionCallStatementNode)
            functionCallStmt((FunctionCallStatementNode) statement);
        else if (statement instanceof LocalNode)
            local((LocalNode) statement);
        else if (statement instanceof LocalFunctionDefinitionNode)
            localFunctionDef((LocalFunctionDefinitionNode) statement);
        else if (statement instanceof RepeatUntilNode)
            repeatUntil((RepeatUntilNode) statement);
        else if (statement instanceof WhileBlockNode)
            whileBlock((WhileBlockNode) statement);
        else if (statement instanceof FunctionDefinitionNode)
            functionDefNode((FunctionDefinitionNode) statement);
    }

    private void assignment(AssignmentNode assignmentNode) {
        for (int i = 0; i < assignmentNode.getVariableListNode().size(); i++) {
            if (assignmentNode.getVariableListNode().getVar(i) instanceof VariableNode) {
                if (i < assignmentNode.getExpressionListNode().size()) {
                    Expression expression = assignmentNode.getExpressionListNode().getExp(i);
                    Type type = expression(expression);
                    System.out.println(type + ": " + assignmentNode.getVariableListNode().getVar(i) + " = " + expression);
                } else {
                    Expression expression = new NilNode();
                    Type type = expression(expression);
                    System.out.println(type + ": " + assignmentNode.getVariableListNode().getVar(i) + " = " + expression);
                }
            }
        }
    }

    private void ifThenElse(IfThenElseBlockNode ifThenElseBlockNode) {
        expression(ifThenElseBlockNode.getIfExpression());
        blockNode(ifThenElseBlockNode.getThenBlockNode());
        blockNode(ifThenElseBlockNode.getElseBlockNode());
    }

    private void doBlock(DoBlockNode doBlockNode) {
        blockNode(doBlockNode.getBlockNode());
    }

    private void forBlock(ForBlockNode forBlockNode) {
        expression(forBlockNode.getStartExpression());
        expression(forBlockNode.getEndExpression());
        expression(forBlockNode.getStepExpression());
        blockNode(forBlockNode.getBlockNode());
    }

    private void forInBlock(ForInBlockNode forInBlockNode) {
        List<Expression> expressions = forInBlockNode.getExpressionListNode().getExpressionList();
        for (Expression expression : expressions) {
            expression(expression);
        }
        blockNode(forInBlockNode.getBlockNode());
    }

    private void functionCallStmt(FunctionCallStatementNode functionCallStatementNode) {
        functionCall(functionCallStatementNode.getCall());
    }

    private void local(LocalNode localNode) {
        List<Expression> expressions = localNode.getExpressionListNode().getExpressionList();
        for (Expression expression : expressions) {
            expression(expression);
        }
    }

    private void localFunctionDef(LocalFunctionDefinitionNode localFunctionDefinitionNode) {
        blockNode(localFunctionDefinitionNode.getBlockNode());
    }

    private void repeatUntil(RepeatUntilNode repeatUntilNode) {
        blockNode(repeatUntilNode.getBlockNode());
        expression(repeatUntilNode.getExpression());
    }

    private void whileBlock(WhileBlockNode whileBlockNode) {
        expression(whileBlockNode.getExpression());
        blockNode(whileBlockNode.getBlockNode());
    }

    private void functionDefNode(FunctionDefinitionNode functionDefinitionNode) {
        blockNode(functionDefinitionNode.getFunctionBody().getBlockNode());
    }

    private void functionCall(FunctionCall functionCall) {
        if (functionCall instanceof FunctionCallNode) {
            prefixExpression(((FunctionCallNode) functionCall).getPreExp());
            List<Expression> expressions = ((FunctionCallNode) functionCall).getExpressionListNode().getExpressionList();
            for (Expression expression : expressions)
                expression(expression);
        } else if (functionCall instanceof FunctionCallSelfNode) {
            prefixExpression(((FunctionCallSelfNode) functionCall).getPreExp());
            List<Expression> expressions = ((FunctionCallSelfNode) functionCall).getExpressionListNode().getExpressionList();
            for (Expression expression : expressions)
                expression(expression);
        }
    }

    private Type expression(Expression expression) {
        if (expression instanceof BooleanExpressionNode)
            return booleanExpression((BooleanExpressionNode) expression);
        else if (expression instanceof BinaryOperationNode)
            return binaryOperationNode((BinaryOperationNode) expression);
        else if (expression instanceof FunctionExpressionNode)
            return functionExpressionNode((FunctionExpressionNode) expression);
        else if (expression instanceof LiteralStringExpressionNode)
            return literalStringExpression((LiteralStringExpressionNode) expression);
        else if (expression instanceof NumeralExpressionNode)
            return numeralExpression((NumeralExpressionNode) expression);
        else if (expression instanceof PrefixExpressionNode)
            return prefixExpression(((PrefixExpressionNode) expression).getPreExp());
        else if (expression instanceof TableConstructorExpressionNode)
            return tableConstructor(((TableConstructorExpressionNode) expression).getTableCons());
        else if (expression instanceof UnaryOperationNode)
            return unaryOperation((UnaryOperationNode) expression);
        return Type.UNDEFINED;
    }

    private Type booleanExpression(BooleanExpressionNode booleanExpressionNode) {
        return Type.BOOLEAN;
    }

    private Type binaryOperationNode(BinaryOperationNode binaryOperationNode) {
        Type t1 = expression(binaryOperationNode.getLeftExpression());
        Type t2 = expression(binaryOperationNode.getRightExpression());

        try {
            return checkTypeBinaryOperation(binaryOperationNode.getOperation(), t1, t2);
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
            return Type.UNDEFINED;
        }
    }

    private Type functionExpressionNode(FunctionExpressionNode functionExpressionNode) {
        blockNode(functionExpressionNode.getBlockNode());
        return Type.FUNCTION;
    }

    private Type literalStringExpression(LiteralStringExpressionNode literalStringExpressionNode) {
        return Type.STRING;
    }

    private Type numeralExpression(NumeralExpressionNode numeralExpressionNode) {
        if (numeralExpressionNode.getType() == NumeralExpressionNode.Type.DOUBLE)
            return Type.DOUBLE;
        else
            return Type.INTEGER;
    }

    private Type prefixExpression(PrefixExpression prefixExpression) {
        if (prefixExpression instanceof PrefixExpressionLPExpressionRPNode) {
            return expression(((PrefixExpressionLPExpressionRPNode) prefixExpression).getExpression());
        }
        return Type.FUNCTION;
    }

    private Type tableConstructor(TableConstructorNode tableConstructorNode) {
        return Type.TABLE;
    }

    private Type unaryOperation(UnaryOperationNode unaryOperationNode) {
        System.out.print(unaryOperationNode);
        Type t = expression(unaryOperationNode.getExpression());

        try {
            return checkTypeUnaryOperation(unaryOperationNode.getOperation(), t);
        } catch (Exception exc) {
            throw new RuntimeException(exc.getMessage());
        }
    }


    private Type checkTypeBinaryOperation(Operation operation, Type leftExpressionType, Type rightExpressionType) throws Exception {
        switch (operation.getType()) {
            case STRING:
                if (operation == Operation.CONCAT &&
                        (leftExpressionType == Type.BOOLEAN || rightExpressionType == Type.BOOLEAN ||
                                leftExpressionType == Type.TABLE || rightExpressionType == Type.TABLE ||
                                leftExpressionType == Type.FUNCTION || rightExpressionType == Type.FUNCTION)) {
                    throw new Exception("Сoncatenation cannot be applied to (" + (leftExpressionType.name() + " "+operation.toString()+" " + rightExpressionType.name()) + ")");
                }
                return Type.STRING;
            case BITWISE:
                if (!(leftExpressionType == Type.INTEGER && rightExpressionType == Type.INTEGER))
                    throw new Exception("Bitwise operation cannot be applied to (" + (leftExpressionType.name() + " "+operation.toString()+" " + rightExpressionType.name()) + ")");
                return Type.INTEGER;
            case RELATION:
                // TODO: Сделать проверку операций отношения
                return null;
            case ARITHMETIC:
                if (!((leftExpressionType == Type.INTEGER || leftExpressionType == Type.DOUBLE) &&
                        (rightExpressionType == Type.INTEGER || rightExpressionType == Type.DOUBLE))) {
                    throw new Exception("Arithmetic operation cannot be applied to (" + (leftExpressionType.name() + " "+operation.toString()+" " + rightExpressionType.name()) + ")");
                }
                if (leftExpressionType == Type.DOUBLE && rightExpressionType == Type.DOUBLE)
                    return Type.DOUBLE;
                else if (leftExpressionType == Type.INTEGER && rightExpressionType == Type.DOUBLE || leftExpressionType == Type.DOUBLE)
                    return Type.DOUBLE;
                else
                    return Type.INTEGER;
        }
        throw new Exception("Undefined operation");
    }

    private Type checkTypeUnaryOperation(Operation operation, Type type) throws Exception {
        switch (operation) {
            case BNOT:
                if (type != Type.INTEGER)
                    throw new Exception("Bitwise operation cannot be applied to (" + type.name() + ")");
                return Type.INTEGER;
            case LENGTH:
                if (type != Type.STRING)
                    throw new Exception("String operation cannot be applied to (" + type.name() + ")");
                return Type.STRING;
            case NOT:
                return Type.BOOLEAN;
            case UNMINUS:
                if (type != Type.INTEGER && type != Type.DOUBLE)
                    throw new Exception("Arithmetic operation cannot be applied to (" + type.name() + ")");
                return type;
        }
        throw new Exception("Undefined operation");
    }


    private enum Type {
        STRING,
        BOOLEAN,
        INTEGER,
        DOUBLE,
        TABLE,
        FUNCTION,
        UNDEFINED
    }
}
