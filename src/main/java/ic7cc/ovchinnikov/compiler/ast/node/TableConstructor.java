package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

public class TableConstructor extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    public FieldList fieldList;

    public TableConstructor (FieldList fieldList) {
        this.fieldList = fieldList;

        if (fieldList != null)
            fieldList.setParent(this);
    }

    @Override
    public ASTNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (fieldList != null)
            fieldList.accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);

        if (fieldList != null)
            fieldList.traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (fieldList != null)
            fieldList.traverseBottomUp(visitor);

        accept(visitor);
    }
    
}
