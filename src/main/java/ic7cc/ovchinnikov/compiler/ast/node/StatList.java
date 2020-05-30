package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class StatList extends ASTNode {

    private final List<Stat> statList;
    @JsonIgnore
    private ASTNode parent;

    public StatList() {
        statList = new LinkedList<>();
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
        statList.add(anItem);
        return this;
    }

    public List<Stat> elements() {
        return Collections.unmodifiableList(statList);
    }

    public Stat getStat(int index) {
        return statList.get(index);
    }

    public void setStat(int index, Stat item) {
        if (item != null) {
            item.setParent(this);
            statList.set(index, item);
        }
    }

    public void addStat(int index, Stat item) {
        if (item != null) {
            item.setParent(this);
            statList.add(index, item);
        }
    }

    public void removeStat(int index) {
        statList.remove(index);
    }

    public int size() {
        return statList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return statList.isEmpty();
    }

    public boolean contains(Stat item) {
        for (Stat stat : statList)
            if (item.equals(stat))
                return true;
        return false;
    }

    public int indexOf(Stat item) {
        return statList.indexOf(item);
    }
    
}
