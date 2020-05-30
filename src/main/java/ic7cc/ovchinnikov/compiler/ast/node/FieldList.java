package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import ic7cc.ovchinnikov.compiler.parser.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class FieldList extends ASTNode {

    private final List<Field> fieldList;

    @JsonIgnore
    private ASTNode parent;

    public FieldList() {
        fieldList = new LinkedList<>();
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
        fieldList.add(anItem);
        return this;
    }

    public List<Field> elements() {
        return Collections.unmodifiableList(fieldList);
    }

    public Field getField(int index) {
        return fieldList.get(index);
    }

    public void setField(int index, Field item) {
        item.setParent(this);
        fieldList.set(index, item);
    }

    public void addField(int index, Field item) {
        item.setParent(this);
        fieldList.add(index, item);
    }

    public void remove(int index) {
        fieldList.remove(index);
    }

    public int size() {
        return fieldList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return fieldList.isEmpty();
    }

    public boolean contains(Field item) {
        for (Field field : fieldList)
            if (item.equals(field))
                return true;
        return false;
    }

    public int indexOf(Field item) {
        return fieldList.indexOf(item);
    }

}
