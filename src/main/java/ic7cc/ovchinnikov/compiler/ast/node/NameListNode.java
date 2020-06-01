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
public class NameListNode extends ASTNode {

    private final List<NameNode> nameNodeList;
    @JsonIgnore
    private ASTNode parent;

    public NameListNode() {
        nameNodeList = new LinkedList<>();
    }

    public NameListNode(NameNode anItem) {
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

    public NameListNode append(NameNode anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        nameNodeList.add(anItem);
        return this;
    }

    public List<NameNode> elements() {
        return Collections.unmodifiableList(nameNodeList);
    }

    public NameNode getName(int index) {
        return nameNodeList.get(index);
    }

    public void setName(int index, NameNode item) {
        item.setParent(this);
        nameNodeList.set(index, item);
    }

    public void addName(int index, NameNode item) {
        item.setParent(this);
        nameNodeList.add(index, item);
    }

    public void removeName(int index) {
        nameNodeList.remove(index);
    }

    public int size() {
        return nameNodeList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return nameNodeList.isEmpty();
    }

    public boolean contains(NameNode item) {
        for (NameNode nameNode : nameNodeList)
            if (item.equals(nameNode))
                return true;
        return false;
    }

    public int indexOf(NameNode item) {
        return nameNodeList.indexOf(item);
    }

}
