package ic7cc.ovchinnikov.compiler.ast;

import ic7cc.ovchinnikov.compiler.ast.impl.VisitorAdaptor;
import ic7cc.ovchinnikov.compiler.ast.node.*;

public abstract class VisitorAdapterGeneric extends VisitorAdaptor {

    @Override
    public void visit(StatementListNode statementListNode) {
        visitGeneric(statementListNode);
    }

    @Override
    public void visit(VariableListNode variableListNode) {
        visitGeneric(variableListNode);
    }

    @Override
    public void visit(NameListNode nameListNode) {
        visitGeneric(nameListNode);
    }

    @Override
    public void visit(ExpressionListNode expressionListNode) {
        visitGeneric(expressionListNode);
    }

    @Override
    public void visit(FieldListNode fieldListNode) {
        visitGeneric(fieldListNode);
    }

    @Override
    public void visit(BlockNode blockNode) {
        visitGeneric(blockNode);
    }

    @Override
    public void visit(NameNode nameNode) {
        visitGeneric(nameNode);
    }

    @Override
    public void visit(FunctionCall functionCall) {
        visitGeneric(functionCall);
    }

    @Override
    public void visit(FunctionExpressionNode functionExp) {
        visitGeneric(functionExp);
    }

    @Override
    public void visit(TableConstructorNode tableConstructorNode) {
        visitGeneric(tableConstructorNode);
    }

    @Override
    public void visit(ReturnStatement lastStat) {
        visitGeneric(lastStat);
    }

    @Override
    public void visit(ReturnNode lastReturnNode) {
        visitGeneric(lastReturnNode);
    }

    @Override
    public void visit(BreakNode lastBreakNode) {
        visitGeneric(lastBreakNode);
    }

    @Override
    public void visit(Statement statement) {
        visitGeneric(statement);
    }

    @Override
    public void visit(AssignmentNode asm) {
        visitGeneric(asm);
    }

    @Override
    public void visit(FunctionCallStatementNode funcCallStmt) {
        visitGeneric(funcCallStmt);
    }

    @Override
    public void visit(FunctionCallNode functionCallNode) {
        visitGeneric(functionCallNode);
    }

    @Override
    public void visit(DoBlockNode doExp) {
        visitGeneric(doExp);
    }

    @Override
    public void visit(WhileBlockNode whileExp) {
        visitGeneric(whileExp);
    }

    @Override
    public void visit(RepeatUntilNode repeatUntilNode) {
        visitGeneric(repeatUntilNode);
    }

    @Override
    public void visit(IfThenElseBlockNode ifThenElse) {
        visitGeneric(ifThenElse);
    }

    @Override
    public void visit(ForBlockNode forExp) {
        visitGeneric(forExp);
    }

    @Override
    public void visit(ForInBlockNode forIn) {
        visitGeneric(forIn);
    }

    @Override
    public void visit(FunctionName functionName) {
        visitGeneric(functionName);
    }

    @Override
    public void visit(FunctionNameVarNode funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(FunctionNameColonVarNode funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(FunctionNameVarDotFunctionNameNode funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(LocalFunctionDefinitionNode localFuncDef) {
        visitGeneric(localFuncDef);
    }

    @Override
    public void visit(LocalNode localNode) {
        visitGeneric(localNode);
    }

    @Override
    public void visit(Expression expression) {
        visitGeneric(expression);
    }

    @Override
    public void visit(NilNode nilNode) {
        visitGeneric(nilNode);
    }

    @Override
    public void visit(BooleanExpressionNode booleanExpressionNode) {
        visitGeneric(booleanExpressionNode);
    }

    @Override
    public void visit(NumeralExpressionNode numberExp) {
        visitGeneric(numberExp);
    }

    @Override
    public void visit(LiteralStringExpressionNode textExp) {
        visitGeneric(textExp);
    }

    @Override
    public void visit(DotDotDotNode dotDotDotNode) {
        visitGeneric(dotDotDotNode);
    }

    @Override
    public void visit(PrefixExpressionNode preExp) {
        visitGeneric(preExp);
    }

    @Override
    public void visit(TableConstructorExpressionNode tableConstructorExp) {
        visitGeneric(tableConstructorExp);
    }

    @Override
    public void visit(BinaryOperationNode binaryOperationNode) {
        visitGeneric(binaryOperationNode);
    }

    @Override
    public void visit(UnaryOperationNode unaryOperationNode) {
        visitGeneric(unaryOperationNode);
    }

    @Override
    public void visit(Variable variable) {
        visitGeneric(variable);
    }

    @Override
    public void visit(VariableNode variableNode) {
        visitGeneric(variableNode);
    }

    @Override
    public void visit(VariableTabIndexNode varTabIndex) {
        visitGeneric(varTabIndex);
    }

    @Override
    public void visit(PrefixExpression prefixExpression) {
        visitGeneric(prefixExpression);
    }

    @Override
    public void visit(PrefixExpressionVariableNode prefixExpressionVariableNode) {
        visitGeneric(prefixExpressionVariableNode);
    }

    @Override
    public void visit(PrefixExpressionFunctionCallNode prefixExpressionFunctionCallNode) {
        visitGeneric(prefixExpressionFunctionCallNode);
    }

    @Override
    public void visit(PrefixExpressionLPExpressionRPNode prefixExpressionLPExpressionRPNode) {
        visitGeneric(prefixExpressionLPExpressionRPNode);
    }

    @Override
    public void visit(Field field) {
        visitGeneric(field);
    }

    @Override
    public void visit(FieldLeftRightExpressionNode fieldLRExp) {
        visitGeneric(fieldLRExp);
    }

    @Override
    public void visit(FieldNameExpressionNode fieldNameExpressionNode) {
        visitGeneric(fieldNameExpressionNode);
    }

    @Override
    public void visit(FieldExpressionNode fieldExpressionNode) {
        visitGeneric(fieldExpressionNode);
    }

    @SuppressWarnings("unused")
    public void visitGeneric(VisitedNode node) {
    }

}
