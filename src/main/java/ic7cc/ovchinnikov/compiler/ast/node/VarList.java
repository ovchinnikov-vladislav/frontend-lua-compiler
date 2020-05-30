package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VarList extends ASTNode {

    private final List<Var> items;
    private ASTNode parent;

    public VarList() {
        items = new LinkedList<>();
    }

    public VarList(Var anItem) {
        this();
        append(anItem);
    }

    public VarList append(Var anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        items.add(anItem);
        return this;
    }

    public List<Var> elements() {
        return Collections.unmodifiableList(items);
    }

    public Var getVar(int index) {
        return items.get(index);
    }

    public void setVar(int index, Var item) {
        item.setParent(this);
        items.set(index, item);
    }

    public void addVar(int index, Var item) {
        item.setParent(this);
        items.add(index, item);
    }

    public void removeVar(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(Var item) {
        for (Var var : items)
            if (item.equals(var))
                return true;
        return false;
    }

    public int indexOf(Var item) {
        return items.indexOf(item);
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
