package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.VisitedNode;
import ic7cc.ovchinnikov.compiler.ast.VisitorAdapterGeneric;
import ic7cc.ovchinnikov.compiler.ast.impl.VisitorAdaptor;

public class FuncSyntactic {

    static final class FuncNameToPrefixExpVisitor extends VisitorAdaptor {
        public Var var;

        public PrefixExp prefixexp;

        @Override
        public void visit(FuncNameColonVar funcNameColonVar) {
            Variable var2 = new Variable(funcNameColonVar.getSelfFuncName().getName());
            var2.setStart(funcNameColonVar.getSelfFuncName().getStart());
            var2.setEnd(funcNameColonVar.getSelfFuncName().getEnd());
            PrefixExp prefixExp = new PrefixExpVar(var2);
            prefixExp.setStart(var2.getStart());
            prefixExp.setEnd(var2.getEnd());

            LiteralStringExp selfvar = new LiteralStringExp(funcNameColonVar.getFuncName().getName());
            selfvar.setStart(funcNameColonVar.getFuncName().getStart());
            selfvar.setEnd(funcNameColonVar.getFuncName().getEnd());

            VarTabIndex head = new VarTabIndex(prefixExp, selfvar);
            head.setStart(prefixExp.getStart());
            head.setStart(selfvar.getEnd());

            var = head;
        }

        @Override
        public void visit(FuncNameVar funcNameVar) {
            // TODO not correct
            prefixexp = new PrefixExpExp(new LiteralStringExp(funcNameVar.getName().getName()));
        }

        @Override
        public void visit(FuncNameVarDotFuncName funcNameVarDotFuncName) {
            if (funcNameVarDotFuncName.getFuncNameList() instanceof FuncNameVar) {
                FuncNameVar fn = (FuncNameVar) funcNameVarDotFuncName.getFuncNameList();
                var = new VarTabIndex(new PrefixExpVar(new Variable(funcNameVarDotFuncName.getName().getName())),
                        new LiteralStringExp(fn.getName().getName()));
            } else {
                // TODO not correct
                VarTabIndex newHead = new VarTabIndex(new PrefixExpVar(new Variable(funcNameVarDotFuncName.getName().getName())),
                        null);
                newHead.setStart(funcNameVarDotFuncName.getStart());
                newHead.setEnd(funcNameVarDotFuncName.getEnd());
                funcNameVarDotFuncName.getName().accept(this);
                newHead.setIndexExp(new PreExp(new PrefixExpVar(var)));
                var = newHead;
            }
        }
    }

    private static final class DoesFuncNameHaveADDotCall extends VisitorAdaptor {

        public boolean ddotcall = false;

        public DoesFuncNameHaveADDotCall() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void visit(FuncNameColonVar funcNameColonVar) {
            ddotcall = true;
        }

        @Override
        public void visit(FuncNameVar funcNameVar) {
        }

        @Override
        public void visit(FuncNameVarDotFuncName funcNameVarDotFuncName) {
            funcNameVarDotFuncName.childrenAccept(this);
        }
    }

    public static VarList funcNameToVarList(FuncName fn) {
        VarList vl = new VarList();
        if (fn instanceof FuncNameVar) {
            Variable var = new Variable(((FuncNameVar) fn).getName().getName());
            var.setStart(((FuncNameVar) fn).getStart());
            var.setEnd(((FuncNameVar) fn).getEnd());

            vl.append(var);
            return vl;
        } else if (fn instanceof FuncNameColonVar) {
            FuncNameColonVar ddotVar = (FuncNameColonVar) fn;
            VarTabIndex head = new VarTabIndex(new PrefixExpVar(new Variable(ddotVar.getSelfFuncName().getName())), new LiteralStringExp(
                    ddotVar.getFuncName().getName()));
            head.setStart(ddotVar.getStart());
            head.setEnd(ddotVar.getEnd());
            vl.append(head);
            return vl;
        } else if (fn instanceof FuncNameVarDotFuncName) {
            // construct a tree

            FuncNameToPrefixExpVisitor ad = new FuncNameToPrefixExpVisitor();
            fn.accept(ad);
            vl.append(ad.var);
            return vl;

        } else {
            throw new RuntimeException("Should not be here!!!");
        }
    }

    public static ExpList methodDefinitionToExpression(FuncName fn, FuncBody fb) {
        ExpList ex = new ExpList();
        NameList nl = fb.getArgs();

        // does the funcname contain a : ? If yes, add self as first argument
        DoesFuncNameHaveADDotCall d = new DoesFuncNameHaveADDotCall();
        fn.accept(d);
        if (d.ddotcall) {
            final NameList newnl = new NameList(new Name("self"));
            nl.childrenAccept(new VisitorAdapterGeneric() {
                @Override
                public void visitGeneric(VisitedNode node) {
                    newnl.append((Name) node);
                }
            });
            nl = newnl;
        }
        FunctionExp exp = new FunctionExp(nl, fb.getVarArgs(), fb.getBlock());
        exp.setStart(nl.getStart());
        exp.setEnd(fb.getBlock().getEnd());
        ex.append(exp);
        return ex;
    }

}
