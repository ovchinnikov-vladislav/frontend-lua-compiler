package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class VarList extends ASTNode {

    private final List<Var> varList;
    @JsonIgnore
    private ASTNode parent;

    public VarList() {
        varList = new LinkedList<>();
    }

    public VarList(Var anItem) {
        this();
        append(anItem);
    }

    public VarList append(Var anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        varList.add(anItem);
        return this;
    }

    public List<Var> elements() {
        return Collections.unmodifiableList(varList);
    }

    public Var getVar(int index) {
        return varList.get(index);
    }

    public void setVar(int index, Var item) {
        item.setParent(this);
        varList.set(index, item);
    }

    public void addVar(int index, Var item) {
        item.setParent(this);
        varList.add(index, item);
    }

    public void removeVar(int index) {
        varList.remove(index);
    }

    public int size() {
        return varList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return varList.isEmpty();
    }

    public boolean contains(Var item) {
        for (Var var : varList)
            if (item.equals(var))
                return true;
        return false;
    }

    public int indexOf(Var item) {
        return varList.indexOf(item);
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
        for (int i = 0; i < size(); i++)
            if (getVar(i) != null)
                getVar(i).accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        this.accept(visitor);
        for (int i = 0; i < size(); i++)
            if (getVar(i) != null)
                getVar(i).traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        for (int i = 0; i < size(); i++)
            if (getVar(i) != null)
                getVar(i).traverseBottomUp(visitor);
        this.accept(visitor);
    }
}
