package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class FieldListNode extends ASTNode {

    private final List<Field> fieldList;

    @JsonIgnore
    private ASTNode parent;

    public FieldListNode() {
        fieldList = new LinkedList<>();
    }

    public FieldListNode(Field anItem) {
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

    public FieldListNode append(Field anItem) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Field field : fieldList) {
            builder.append(field).append(",");
        }
        String args = builder.toString();
        if (!args.trim().isEmpty() && args.charAt(args.length() - 1) == ',')
            args = args.substring(0, args.length() - 1);
        return args;
    }
}
