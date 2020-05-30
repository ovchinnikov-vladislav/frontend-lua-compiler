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
public class NameList extends ASTNode {

    private final List<Name> nameList;
    @JsonIgnore
    private ASTNode parent;

    public NameList() {
        nameList = new LinkedList<>();
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
        nameList.add(anItem);
        return this;
    }

    public List<Name> elements() {
        return Collections.unmodifiableList(nameList);
    }

    public Name getName(int index) {
        return nameList.get(index);
    }

    public void setName(int index, Name item) {
        item.setParent(this);
        nameList.set(index, item);
    }

    public void addName(int index, Name item) {
        item.setParent(this);
        nameList.add(index, item);
    }

    public void removeName(int index) {
        nameList.remove(index);
    }

    public int size() {
        return nameList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return nameList.isEmpty();
    }

    public boolean contains(Name item) {
        for (Name name : nameList)
            if (item.equals(name))
                return true;
        return false;
    }

    public int indexOf(Name item) {
        return nameList.indexOf(item);
    }

}
