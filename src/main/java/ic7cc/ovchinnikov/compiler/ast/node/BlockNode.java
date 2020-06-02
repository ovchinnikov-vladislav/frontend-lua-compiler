package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
    public String toString() {
        return  statementListNode + " " + returnStatement;
    }
}
