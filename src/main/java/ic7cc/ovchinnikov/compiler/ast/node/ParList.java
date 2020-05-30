package ic7cc.ovchinnikov.compiler.ast.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.Visitor;
import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParList extends ASTNode {

    @JsonIgnore
    private ASTNode parent;
    private NameList nameList;
    private Boolean varParList;

    public ParList(NameList nameList, Boolean varParList) {
        this.nameList = nameList;
        this.varParList = varParList;
    }

    @Override
    public void accept(Visitor visitor) {
        throw new RuntimeException("Should not be here!");

    }

    @Override
    public void childrenAccept(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public void traverseBottomUp(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public void traverseTopDown(Visitor visitor) {
        throw new RuntimeException("Should not be here!");
    }
}
