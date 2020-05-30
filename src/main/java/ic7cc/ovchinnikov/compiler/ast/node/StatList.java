package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StatList extends ASTNode {

    private final List<Stat> items;
    private ASTNode parent;

    public StatList() {
        items = new LinkedList<>();
    }

    public StatList(Stat anItem) {
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
            if (getStat(i) != null)
                getStat(i).accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        this.accept(visitor);
        for (int i = 0; i < size(); i++)
            if (getStat(i) != null)
                getStat(i).traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        for (int i = 0; i < size(); i++)
            if (getStat(i) != null)
                getStat(i).traverseBottomUp(visitor);
        this.accept(visitor);
    }

    public StatList append(Stat anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        items.add(anItem);
        return this;
    }

    public List<Stat> elements() {
        return Collections.unmodifiableList(items);
    }

    public Stat getStat(int index) {
        return items.get(index);
    }

    public void setStat(int index, Stat item) {
        item.setParent(this);
        items.set(index, item);
    }

    public void addStat(int index, Stat item) {
        item.setParent(this);
        items.add(index, item);
    }

    public void removeStat(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(Stat item) {
        for (Stat stat : items)
            if (item.equals(stat))
                return true;
        return false;
    }

    public int indexOf(Stat item) {
        return items.indexOf(item);
    }

}
