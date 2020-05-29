package ic7cc.ovchinnikov.compiler.ast.node;

import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Exp extends ASTNode {

    private ASTNode parent;

}
