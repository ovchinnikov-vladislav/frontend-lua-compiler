package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.VisitedNode;
import ic7cc.ovchinnikov.compiler.ast.VisitorAdapterGeneric;
import ic7cc.ovchinnikov.compiler.ast.impl.VisitorAdaptor;

public class FunctionSyntactic {

    static final class FuncNameToPrefixExpVisitor extends VisitorAdaptor {
        public Variable variable;

        public PrefixExpression prefixexp;

        @Override
        public void visit(FunctionNameColonVarNode functionNameColonVarNode) {
            VariableNode var2 = new VariableNode(functionNameColonVarNode.getSelfFuncNameNode().getName());
            var2.setStart(functionNameColonVarNode.getSelfFuncNameNode().getStart());
            var2.setEnd(functionNameColonVarNode.getSelfFuncNameNode().getEnd());
            PrefixExpression prefixExpression = new PrefixExpressionVariableNode(var2);
            prefixExpression.setStart(var2.getStart());
            prefixExpression.setEnd(var2.getEnd());

            LiteralStringExpressionNode selfvar = new LiteralStringExpressionNode(functionNameColonVarNode.getFuncNameNode().getName());
            selfvar.setStart(functionNameColonVarNode.getFuncNameNode().getStart());
            selfvar.setEnd(functionNameColonVarNode.getFuncNameNode().getEnd());

            VariableTabIndexNode head = new VariableTabIndexNode(prefixExpression, selfvar);
            head.setStart(prefixExpression.getStart());
            head.setStart(selfvar.getEnd());

            variable = head;
        }

        @Override
        public void visit(FunctionNameVarNode funcNameVar) {
            // TODO not correct
            prefixexp = new PrefixExpressionExpressionNode(new LiteralStringExpressionNode(funcNameVar.getNameNode().getName()));
        }

        @Override
        public void visit(FunctionNameVarDotFunctionNameNode funcNameVarDotFuncName) {
            if (funcNameVarDotFuncName.getFunctionNameList() instanceof FunctionNameVarNode) {
                FunctionNameVarNode fn = (FunctionNameVarNode) funcNameVarDotFuncName.getFunctionNameList();
                variable = new VariableTabIndexNode(new PrefixExpressionVariableNode(new VariableNode(funcNameVarDotFuncName.getNameNode().getName())),
                        new LiteralStringExpressionNode(fn.getNameNode().getName()));
            } else {
                // TODO not correct
                VariableTabIndexNode newHead = new VariableTabIndexNode(new PrefixExpressionVariableNode(new VariableNode(funcNameVarDotFuncName.getNameNode().getName())),
                        null);
                newHead.setStart(funcNameVarDotFuncName.getStart());
                newHead.setEnd(funcNameVarDotFuncName.getEnd());
                funcNameVarDotFuncName.getNameNode().accept(this);
                newHead.setIndexExpression(new PrefixExpressionNode(new PrefixExpressionVariableNode(variable)));
                variable = newHead;
            }
        }
    }

    private static final class DoesFuncNameHaveADDotCall extends VisitorAdaptor {

        public boolean ddotcall = false;

        public DoesFuncNameHaveADDotCall() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void visit(FunctionNameColonVarNode functionNameColonVarNode) {
            ddotcall = true;
        }

        @Override
        public void visit(FunctionNameVarNode funcNameVar) {
        }

        @Override
        public void visit(FunctionNameVarDotFunctionNameNode funcNameVarDotFuncName) {
            funcNameVarDotFuncName.childrenAccept(this);
        }
    }

    public static VariableListNode funcNameToVarList(FunctionName fn) {
        VariableListNode vl = new VariableListNode();
        if (fn instanceof FunctionNameVarNode) {
            VariableNode var = new VariableNode(((FunctionNameVarNode) fn).getNameNode().getName());
            var.setStart(((FunctionNameVarNode) fn).getStart());
            var.setEnd(((FunctionNameVarNode) fn).getEnd());

            vl.append(var);
            return vl;
        } else if (fn instanceof FunctionNameColonVarNode) {
            FunctionNameColonVarNode ddotVar = (FunctionNameColonVarNode) fn;
            VariableTabIndexNode head = new VariableTabIndexNode(new PrefixExpressionVariableNode(new VariableNode(ddotVar.getSelfFuncNameNode().getName())), new LiteralStringExpressionNode(
                    ddotVar.getFuncNameNode().getName()));
            head.setStart(ddotVar.getStart());
            head.setEnd(ddotVar.getEnd());
            vl.append(head);
            return vl;
        } else if (fn instanceof FunctionNameVarDotFunctionNameNode) {
            // construct a tree

            FuncNameToPrefixExpVisitor ad = new FuncNameToPrefixExpVisitor();
            fn.accept(ad);
            vl.append(ad.variable);
            return vl;

        } else {
            throw new RuntimeException("Should not be here!!!");
        }
    }

    public static ExpressionListNode methodDefinitionToExpression(FunctionName fn, FunctionBodyNode fb) {
        ExpressionListNode ex = new ExpressionListNode();
        NameListNode nl = fb.getArgs();

        // does the funcname contain a : ? If yes, add self as first argument
        DoesFuncNameHaveADDotCall d = new DoesFuncNameHaveADDotCall();
        fn.accept(d);
        if (d.ddotcall) {
            final NameListNode newnl = new NameListNode(new NameNode("self"));
            nl.childrenAccept(new VisitorAdapterGeneric() {
                @Override
                public void visitGeneric(VisitedNode node) {
                    newnl.append((NameNode) node);
                }
            });
            nl = newnl;
        }
        FunctionExpressionNode exp = new FunctionExpressionNode(nl, fb.getVarArgs(), fb.getBlockNode());
        exp.setStart(nl.getStart());
        exp.setEnd(fb.getBlockNode().getEnd());
        ex.append(exp);
        return ex;
    }

}
