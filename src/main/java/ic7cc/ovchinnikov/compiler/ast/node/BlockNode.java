package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockNode extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    @JacksonXmlProperty(localName = "StatList")
    private StatementListNode statementListNode;
    @JacksonXmlProperty(localName = "Return")
    private ReturnStatement returnStatement;

    public BlockNode(StatementListNode statementListNode, ReturnStatement returnStatement) {
        this.statementListNode = statementListNode;
        if (statementListNode != null)
            statementListNode.setParent(this);
        this.returnStatement = returnStatement;
        if (returnStatement != null)
            returnStatement.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

        if (statementListNode != null)
            statementListNode.accept(visitor);

        if (returnStatement != null)
            returnStatement.accept(visitor);
    }

    @Override
    public void childrenAccept(Visitor visitor) {
        if (statementListNode != null)
            statementListNode.accept(visitor);

        if (returnStatement != null)
            returnStatement.accept(visitor);
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        if (statementListNode != null)
            statementListNode.traverseBottomUp(visitor);

        if (returnStatement != null)
            returnStatement.traverseBottomUp(visitor);

        accept(visitor);
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if (statementListNode != null)
            statementListNode.traverseTopDown(visitor);

        if (returnStatement != null)
            returnStatement.traverseTopDown(visitor);
    }

    @Override
    public String toString() {
        return  statementListNode + " " + returnStatement;
    }
}
