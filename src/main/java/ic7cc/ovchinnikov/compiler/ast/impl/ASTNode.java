package ic7cc.ovchinnikov.compiler.ast.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ic7cc.ovchinnikov.compiler.ast.VisitedNode;
import ic7cc.ovchinnikov.compiler.parser.location.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ASTNode implements VisitedNode {

    @JsonIgnore
    private Location start;
    @JsonIgnore
    private Location marker;
    @JsonIgnore
    private Location end;

    public abstract ASTNode getParent();
    public abstract void setParent(ASTNode parent);

}
