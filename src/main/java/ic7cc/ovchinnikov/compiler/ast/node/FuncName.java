package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FuncName extends ASTNode {

    private ASTNode parent;

}