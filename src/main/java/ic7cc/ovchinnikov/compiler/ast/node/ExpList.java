package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExpList extends ASTNode {

    private final List<Exp> items;
    private ASTNode parent;

    public ExpList() {
        items = new LinkedList<>();
    }

    public ExpList(Exp anItem) {
        this();
        append(anItem);
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
            if (getExp(i) != null)
                getExp(i).accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        this.accept(visitor);
        for (int i = 0; i < size(); i++)
            if (getExp(i) != null)
                getExp(i).traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        for (int i = 0; i < size(); i++)
            if (getExp(i) != null)
                getExp(i).traverseBottomUp(visitor);
        this.accept(visitor);
    }

    public ExpList append(Exp anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        items.add(anItem);
        return this;
    }

    public List<Exp> elements() {
        return Collections.unmodifiableList(items);
    }

    public Exp getExp(int index) {
        return items.get(index);
    }

    public void setExp(int index, Exp item) {
        item.setParent(this);
        items.set(index, item);
    }

    public void addExp(int index, Exp item) {
        item.setParent(this);
        items.add(index, item);
    }

    public void removeExp(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(Exp item) {
        for (Exp exp : items)
            if (item.equals(exp))
                return true;
        return false;
    }

    public int indexOf(Exp item) {
        return items.indexOf(item);
    }

}
