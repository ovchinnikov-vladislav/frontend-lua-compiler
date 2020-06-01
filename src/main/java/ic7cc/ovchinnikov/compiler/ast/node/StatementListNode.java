package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class StatementListNode extends ASTNode {

    private final List<Statement> statementList;
    @JsonIgnore
    private ASTNode parent;

    public StatementListNode() {
        statementList = new LinkedList<>();
    }

    public StatementListNode(Statement anItem) {
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

    public StatementListNode append(Statement anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        statementList.add(anItem);
        return this;
    }

    public List<Statement> elements() {
        return Collections.unmodifiableList(statementList);
    }

    public Statement getStat(int index) {
        return statementList.get(index);
    }

    public void setStat(int index, Statement item) {
        if (item != null) {
            item.setParent(this);
            statementList.set(index, item);
        }
    }

    public void addStat(int index, Statement item) {
        if (item != null) {
            item.setParent(this);
            statementList.add(index, item);
        }
    }

    public void removeStat(int index) {
        statementList.remove(index);
    }

    public int size() {
        return statementList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return statementList.isEmpty();
    }

    public boolean contains(Statement item) {
        for (Statement statement : statementList)
            if (item.equals(statement))
                return true;
        return false;
    }

    public int indexOf(Statement item) {
        return statementList.indexOf(item);
    }
    
}
