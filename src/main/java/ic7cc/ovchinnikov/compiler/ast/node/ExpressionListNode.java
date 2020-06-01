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
public class ExpressionListNode extends ASTNode {

    private final List<Expression> expressionList;

    @JsonIgnore
    private ASTNode parent;

    public ExpressionListNode() {
        expressionList = new LinkedList<>();
    }

    public ExpressionListNode(Expression anItem) {
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

    public ExpressionListNode append(Expression anItem) {
        if (anItem == null)
            return this;
        anItem.setParent(this);
        expressionList.add(anItem);
        return this;
    }

    public List<Expression> elements() {
        return Collections.unmodifiableList(expressionList);
    }

    public Expression getExp(int index) {
        return expressionList.get(index);
    }

    public void setExp(int index, Expression item) {
        item.setParent(this);
        expressionList.set(index, item);
    }

    public void addExp(int index, Expression item) {
        item.setParent(this);
        expressionList.add(index, item);
    }

    public void removeExp(int index) {
        expressionList.remove(index);
    }

    public int size() {
        return expressionList.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return expressionList.isEmpty();
    }

    public boolean contains(Expression item) {
        for (Expression expression : expressionList)
            if (item.equals(expression))
                return true;
        return false;
    }

    public int indexOf(Expression item) {
        return expressionList.indexOf(item);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Expression expression : expressionList) {
            builder.append(expression).append(",");
        }
        String args = builder.toString();
        if (!args.isEmpty() && args.charAt(args.length() - 1) == ',')
            args = args.substring(0, args.length() - 1);
        return args;
    }
}
