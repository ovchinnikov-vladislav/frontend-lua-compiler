package ic7cc.ovchinnikov.compiler.ast;

import ic7cc.ovchinnikov.compiler.ast.impl.VisitorAdaptor;
import ic7cc.ovchinnikov.compiler.ast.node.*;

public abstract class VisitorAdapterGeneric extends VisitorAdaptor {

    @Override
    public void visit(StatList statList) {
        visitGeneric(statList);
    }

    @Override
    public void visit(VarList varList) {
        visitGeneric(varList);
    }

    @Override
    public void visit(NameList nameList) {
        visitGeneric(nameList);
    }

    @Override
    public void visit(ExpList expList) {
        visitGeneric(expList);
    }

    @Override
    public void visit(FieldList fieldList) {
        visitGeneric(fieldList);
    }

    @Override
    public void visit(Block block) {
        visitGeneric(block);
    }

    @Override
    public void visit(Name name) {
        visitGeneric(name);
    }

    @Override
    public void visit(FunctionCall functionCall) {
        visitGeneric(functionCall);
    }

    @Override
    public void visit(FunctionExp functionExp) {
        visitGeneric(functionExp);
    }

    @Override
    public void visit(TableConstructor tableConstructor) {
        visitGeneric(tableConstructor);
    }

    @Override
    public void visit(RetStat lastStat) {
        visitGeneric(lastStat);
    }

    @Override
    public void visit(Return lastReturn) {
        visitGeneric(lastReturn);
    }

    @Override
    public void visit(Break lastBreak) {
        visitGeneric(lastBreak);
    }

    @Override
    public void visit(Stat stat) {
        visitGeneric(stat);
    }

    @Override
    public void visit(Assignment asm) {
        visitGeneric(asm);
    }

    @Override
    public void visit(FuncCallStatement funcCallStmt) {
        visitGeneric(funcCallStmt);
    }

    @Override
    public void visit(FuncCall funcCall) {
        visitGeneric(funcCall);
    }

    @Override
    public void visit(DoBlock doExp) {
        visitGeneric(doExp);
    }

    @Override
    public void visit(WhileBlock whileExp) {
        visitGeneric(whileExp);
    }

    @Override
    public void visit(RepeatUntil repeatUntil) {
        visitGeneric(repeatUntil);
    }

    @Override
    public void visit(IfThenElseBlock ifThenElse) {
        visitGeneric(ifThenElse);
    }

    @Override
    public void visit(ForBlock forExp) {
        visitGeneric(forExp);
    }

    @Override
    public void visit(ForInBlock forIn) {
        visitGeneric(forIn);
    }

    @Override
    public void visit(FuncName funcName) {
        visitGeneric(funcName);
    }

    @Override
    public void visit(FuncNameVar funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(FuncNameColonVar funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(FuncNameVarDotFuncName funcNameVar) {
        visitGeneric(funcNameVar);
    }

    @Override
    public void visit(LocalFunctionDef localFuncDef) {
        visitGeneric(localFuncDef);
    }

    @Override
    public void visit(LocalDecl localDecl) {
        visitGeneric(localDecl);
    }

    @Override
    public void visit(Exp exp) {
        visitGeneric(exp);
    }

    @Override
    public void visit(Nil nil) {
        visitGeneric(nil);
    }

    @Override
    public void visit(BooleanExp booleanExp) {
        visitGeneric(booleanExp);
    }

    @Override
    public void visit(NumeralExp numberExp) {
        visitGeneric(numberExp);
    }

    @Override
    public void visit(LiteralStringExp textExp) {
        visitGeneric(textExp);
    }

    @Override
    public void visit(Dots dots) {
        visitGeneric(dots);
    }

    @Override
    public void visit(PreExp preExp) {
        visitGeneric(preExp);
    }

    @Override
    public void visit(TableConstructorExp tableConstructorExp) {
        visitGeneric(tableConstructorExp);
    }

    @Override
    public void visit(Binop binop) {
        visitGeneric(binop);
    }

    @Override
    public void visit(Unop unop) {
        visitGeneric(unop);
    }

    @Override
    public void visit(Var var) {
        visitGeneric(var);
    }

    @Override
    public void visit(Variable variable) {
        visitGeneric(variable);
    }

    @Override
    public void visit(VarTabIndex varTabIndex) {
        visitGeneric(varTabIndex);
    }

    @Override
    public void visit(PrefixExp prefixExp) {
        visitGeneric(prefixExp);
    }

    @Override
    public void visit(PrefixExpVar prefixExpVar) {
        visitGeneric(prefixExpVar);
    }

    @Override
    public void visit(PrefixExpFuncCall prefixExpFuncCall) {
        visitGeneric(prefixExpFuncCall);
    }

    @Override
    public void visit(PrefixExpExp prefixExpExp) {
        visitGeneric(prefixExpExp);
    }

    @Override
    public void visit(Field field) {
        visitGeneric(field);
    }

    @Override
    public void visit(FieldLeftRightExp fieldLRExp) {
        visitGeneric(fieldLRExp);
    }

    @Override
    public void visit(FieldNameExp fieldNameExp) {
        visitGeneric(fieldNameExp);
    }

    @Override
    public void visit(FieldExp fieldExp) {
        visitGeneric(fieldExp);
    }

    @SuppressWarnings("unused")
    public void visitGeneric(VisitedNode node) {
    }

}
