package ic7cc.ovchinnikov.compiler.semantic;

import ic7cc.ovchinnikov.compiler.ast.Operation;
import ic7cc.ovchinnikov.compiler.ast.node.*;
import ic7cc.ovchinnikov.compiler.ast.xml.util.SerializeDefinedType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionDataTypeTableBuilder {

    private final Map<String, Type> typeMap = new HashMap<>();

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
        }
    }

    private void returnStatement(ReturnStatement returnStatement) {
        if (returnStatement instanceof ReturnNode) {
            ExpressionListNode expressionListNode = ((ReturnNode) returnStatement).getExpressionListNode();
            if (expressionListNode != null) {
                List<Expression> expressions = expressionListNode.getExpressionList();
                for (Expression expression : expressions) {
                    Type t = expression(expression);
                    System.out.println(t + ": return " + expression);
                }
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
//        else if (statement instanceof LocalFunctionDefinitionNode)
//            localFunctionDefinitionNodeBlockNodeMap.put((LocalFunctionDefinitionNode) statement, ((LocalFunctionDefinitionNode) statement).getBlockNode());
        else if (statement instanceof RepeatUntilNode)
            repeatUntil((RepeatUntilNode) statement);
        else if (statement instanceof WhileBlockNode)
            whileBlock((WhileBlockNode) statement);
//        else if (statement instanceof FunctionDefinitionNode)
//            functionDefinitionNodeMap.put(((FunctionDefinitionNode) statement).getFunctionName(), ((FunctionDefinitionNode) statement).getFunctionBody());
    }

    private void assignment(AssignmentNode assignmentNode) {
        for (int i = 0; i < assignmentNode.getVariableListNode().size(); i++) {
            if (assignmentNode.getVariableListNode().getVar(i) instanceof VariableTabIndexNode) {
                PrefixExpression prefixExpression = ((VariableTabIndexNode) assignmentNode.getVariableListNode().getVar(i)).getPreExp();
                if (prefixExpression instanceof PrefixExpressionLPExpressionRPNode) {
                    Expression expression = ((PrefixExpressionLPExpressionRPNode) prefixExpression).getExpression();
                    Type t = expression(expression);
                    System.out.println(t + ": " + expression);
                } else if (prefixExpression instanceof PrefixExpressionFunctionCallNode) {
                    functionCall(((PrefixExpressionFunctionCallNode) prefixExpression).getCall());
                }

                Expression indexExpression = ((VariableTabIndexNode) assignmentNode.getVariableListNode().getVar(i)).getIndexExpression();
                Type t = expression(indexExpression);
                System.out.println(t + ": " + indexExpression);
            }
            if (i < assignmentNode.getExpressionListNode().size()) {
                Expression expression = assignmentNode.getExpressionListNode().getExp(i);
                Type type = expression(expression);
                System.out.println(type + ": " + assignmentNode.getVariableListNode().getVar(i) + " = " + expression);
                typeMap.put(assignmentNode.getVariableListNode().getVar(i).toString(), type);
            } else {
                Expression expression = new NilNode();
                Type type = expression(expression);
                System.out.println(type + ": " + assignmentNode.getVariableListNode().getVar(i) + " = " + expression);
                typeMap.put(assignmentNode.getVariableListNode().getVar(i).toString(), type);
            }
        }
    }

    private void ifThenElse(IfThenElseBlockNode ifThenElseBlockNode) {
        Type t = expression(ifThenElseBlockNode.getIfExpression());
        System.out.println(t + ": " + ifThenElseBlockNode.getIfExpression());
        blockNode(ifThenElseBlockNode.getThenBlockNode());
        if (ifThenElseBlockNode.getElseBlockNode() != null)
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
            for (Expression expression : expressions) {
                Type t = expression(expression);
                System.out.println(t + ": " + expression);
            }
        } else if (functionCall instanceof FunctionCallSelfNode) {
            prefixExpression(((FunctionCallSelfNode) functionCall).getPreExp());
            List<Expression> expressions = ((FunctionCallSelfNode) functionCall).getExpressionListNode().getExpressionList();
            for (Expression expression : expressions) {
                Type t = expression(expression);
                System.out.println(t + ": " + expression);
            }
        }
    }

    private void field(Field field) {
        if (field instanceof FieldLeftRightExpressionNode) {
            Expression leftExpression = ((FieldLeftRightExpressionNode) field).getLeftExpression();
            Type t1 = expression(leftExpression);
            Expression rightExpression = ((FieldLeftRightExpressionNode) field).getRightExpression();
            Type t2 = expression(rightExpression);

            System.out.println("[" + t1 + ": " + leftExpression + "] = " + t2 + ": " + rightExpression);
        } else if (field instanceof FieldNameExpressionNode) {
            Expression expression = ((FieldNameExpressionNode) field).getExpression();
            Type t = expression(expression);
            System.out.println(t + ": " + expression);
        } else if (field instanceof FieldExpressionNode) {
            Expression expression = ((FieldExpressionNode) field).getFieldExpression();
            Type t = expression(expression);
            System.out.println(t + ": " + expression);
        }
    }

    private Type expression(Expression expression) {
        if (expression instanceof BooleanExpressionNode)
            return Type.BOOLEAN;
        else if (expression instanceof BinaryOperationNode)
            return binaryOperationNode((BinaryOperationNode) expression);
        else if (expression instanceof FunctionExpressionNode)
            return functionExpressionNode((FunctionExpressionNode) expression);
        else if (expression instanceof LiteralStringExpressionNode)
            return Type.STRING;
        else if (expression instanceof NumeralExpressionNode)
            return numeralExpression((NumeralExpressionNode) expression);
        else if (expression instanceof PrefixExpressionNode)
            return prefixExpression(((PrefixExpressionNode) expression).getPreExp());
        else if (expression instanceof TableConstructorExpressionNode)
            return tableConstructor(((TableConstructorExpressionNode) expression).getTableCons());
        else if (expression instanceof UnaryOperationNode)
            return unaryOperation((UnaryOperationNode) expression);
        return Type.NIL;
    }

    private Type binaryOperationNode(BinaryOperationNode binaryOperationNode) {
        Type t1 = expression(binaryOperationNode.getLeftExpression());
        Type t2 = expression(binaryOperationNode.getRightExpression());

        try {
            return checkTypeBinaryOperation(binaryOperationNode.getOperation(), t1, t2);
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
            return Type.NIL;
        }
    }

    private Type functionExpressionNode(FunctionExpressionNode functionExpressionNode) {
        blockNode(functionExpressionNode.getBlockNode());
        return Type.FUNCTION;
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
        } else if (prefixExpression instanceof PrefixExpressionFunctionCallNode) {
            functionCall(((PrefixExpressionFunctionCallNode) prefixExpression).getCall());
        } else if (prefixExpression instanceof PrefixExpressionVariableNode) {
            Type t = typeMap.get(((PrefixExpressionVariableNode) prefixExpression).getVariable().toString());
            return t == null ? Type.NIL : t;
        }
        return Type.NIL;
    }

    private Type tableConstructor(TableConstructorNode tableConstructorNode) {
        List<Field> fields = tableConstructorNode.getFieldListNode().getFieldList();
        for (Field field : fields) {
            field(field);
        }

        return Type.TABLE;
    }

    private Type unaryOperation(UnaryOperationNode unaryOperationNode) {
        Type t = expression(unaryOperationNode.getExpression());

        try {
            return checkTypeUnaryOperation(unaryOperationNode.getOperation(), t);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        return t;
    }


    private Type checkTypeBinaryOperation(Operation operation, Type leftExpressionType, Type rightExpressionType) throws Exception {
        switch (operation.getType()) {
            case STRING:
                if (operation == Operation.CONCAT &&
                        (leftExpressionType == Type.BOOLEAN || rightExpressionType == Type.BOOLEAN ||
                                leftExpressionType == Type.TABLE || rightExpressionType == Type.TABLE ||
                                leftExpressionType == Type.FUNCTION || rightExpressionType == Type.FUNCTION)) {
                    throw new Exception("Сoncatenation cannot be applied to (" + (leftExpressionType.name() + " " + operation.toString() + " " + rightExpressionType.name()) + ")");
                }
                return Type.STRING;
            case BITWISE:
                if (!(leftExpressionType == Type.INTEGER && rightExpressionType == Type.INTEGER))
                    throw new Exception("Bitwise operation cannot be applied to (" + (leftExpressionType.name() + " " + operation.toString() + " " + rightExpressionType.name()) + ")");
                return Type.INTEGER;
            case RELATION:
                if (operation == Operation.EQUAL || operation == Operation.NOTEQ)
                    return Type.BOOLEAN;
                else if (leftExpressionType == Type.INTEGER || leftExpressionType == Type.DOUBLE ||
                        rightExpressionType == Type.INTEGER || rightExpressionType == Type.DOUBLE)
                    return Type.BOOLEAN;
                throw new Exception("Bitwise operation cannot be applied to (" + (leftExpressionType.name() + " " + operation.toString() + " " + rightExpressionType.name()) + ")");
            case ARITHMETIC:
                if (!((leftExpressionType == Type.INTEGER || leftExpressionType == Type.DOUBLE) &&
                        (rightExpressionType == Type.INTEGER || rightExpressionType == Type.DOUBLE))) {
                    throw new Exception("Arithmetic operation cannot be applied to (" + (leftExpressionType.name() + " " + operation.toString() + " " + rightExpressionType.name()) + ")");
                }
                if (leftExpressionType == Type.DOUBLE && rightExpressionType == Type.DOUBLE)
                    return Type.DOUBLE;
                else if (leftExpressionType == Type.INTEGER && rightExpressionType == Type.DOUBLE || leftExpressionType == Type.DOUBLE)
                    return Type.DOUBLE;
                else
                    return Type.INTEGER;
            case BOOLEAN:
                if (leftExpressionType != Type.BOOLEAN || rightExpressionType != Type.BOOLEAN) {
                    if (operation == Operation.ADD)
                        return rightExpressionType;
                    else if (operation == Operation.OR)
                        return leftExpressionType;
                }
                return Type.BOOLEAN;
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
            default:
                return Type.NIL;
        }
    }


    private enum Type {
        STRING,
        BOOLEAN,
        INTEGER,
        DOUBLE,
        TABLE,
        FUNCTION,
        NIL
    }
}
