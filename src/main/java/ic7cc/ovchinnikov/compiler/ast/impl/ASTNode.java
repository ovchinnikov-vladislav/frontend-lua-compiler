package ic7cc.ovchinnikov.compiler.ast.impl;

import ic7cc.ovchinnikov.compiler.ast.VisitedNode;
import ic7cc.ovchinnikov.compiler.parser.location.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ASTNode implements VisitedNode {

    private Location start;
    private Location marker;
    private Location end;

    public abstract ASTNode getParent();
    public abstract void setParent(ASTNode parent);

}
