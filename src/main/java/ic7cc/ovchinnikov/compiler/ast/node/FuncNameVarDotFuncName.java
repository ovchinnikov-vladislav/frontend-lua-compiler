package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;

public class FuncNameVarDotFuncName extends FuncName {

    public Name name;
    public FuncName funcNameList;

    public FuncNameVarDotFuncName (Name name, FuncName funcNameList) {
        this.name = name;
        if (name != null) 
            name.setParent(this);
        
        this.funcNameList = funcNameList;
        if (funcNameList != null) 
            funcNameList.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (name != null) 
            name.accept(visitor);
        
        if (funcNameList != null) 
            funcNameList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        
        if (name != null) 
            name.traverseTopDown(visitor);
        
        if (funcNameList != null) 
            funcNameList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (name != null) 
            name.traverseBottomUp(visitor);
        
        if (funcNameList != null) 
            funcNameList.traverseBottomUp(visitor);
        
        accept(visitor);
    }
}
