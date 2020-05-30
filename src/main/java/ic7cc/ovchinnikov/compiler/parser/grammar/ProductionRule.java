package ic7cc.ovchinnikov.compiler.parser.grammar;

import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import java_cup.runtime.Symbol;

public interface ProductionRule {
	
	ProductionRuleType getType();
	
	ASTNode getResultingNode();
	
	ASTNode[] getReducedNodes();
	
	Symbol[] getSymbols();

}
