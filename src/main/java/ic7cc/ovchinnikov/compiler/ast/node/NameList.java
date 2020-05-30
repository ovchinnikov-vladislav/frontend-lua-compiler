package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NameList extends ASTNode {

    private final List<Name> items;
    private ASTNode parent;

    public NameList() {
        items = new LinkedList<>();
    }

    public NameList(Name anItem) {
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
            if (getName(i) != null)
                getName(i).accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        this.accept(visitor);
        for (int i = 0; i < size(); i++)
            if (getName(i) != null)
                getName(i).traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        for (int i = 0; i < size(); i++)
            if (getName(i) != null)
                getName(i).traverseBottomUp(visitor);
        this.accept(visitor);
    }

    public NameList append(Name anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        items.add(anItem);
        return this;
    }

    public List<Name> elements() {
        return Collections.unmodifiableList(items);
    }

    public Name getName(int index) {
        return items.get(index);
    }

    public void setName(int index, Name item) {
        item.setParent(this);
        items.set(index, item);
    }

    public void addName(int index, Name item) {
        item.setParent(this);
        items.add(index, item);
    }

    public void removeName(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(Name item) {
        for (Name name : items)
            if (item.equals(name))
                return true;
        return false;
    }

    public int indexOf(Name item) {
        return items.indexOf(item);
    }

}
