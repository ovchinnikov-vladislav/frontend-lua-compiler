package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import ic7cc.ovchinnikov.compiler.parser.location.Location;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FieldList extends ASTNode {

    private final List<Field> items;
    private ASTNode parent;

    public FieldList() {
        items = new LinkedList<>();
    }

    public FieldList(Field anItem) {
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
            if (getField(i) != null)
                getField(i).accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        this.accept(visitor);
        for (int i = 0; i < size(); i++)
            if (getField(i) != null)
                getField(i).traverseTopDown(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        for (int i = 0; i < size(); i++)
            if (getField(i) != null)
                getField(i).traverseBottomUp(visitor);
        this.accept(visitor);
    }

    public FieldList append(Field anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        items.add(anItem);
        return this;
    }

    public List<Field> elements() {
        return Collections.unmodifiableList(items);
    }

    public Field getField(int index) {
        return items.get(index);
    }

    public void setField(int index, Field item) {
        item.setParent(this);
        items.set(index, item);
    }

    public void addField(int index, Field item) {
        item.setParent(this);
        items.add(index, item);
    }

    public void remove(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(Field item) {
        for (Field field : items)
            if (item.equals(field))
                return true;
        return false;
    }

    public int indexOf(Field item) {
        return items.indexOf(item);
    }

}
