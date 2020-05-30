package ic7cc.ovchinnikov.compiler.parser.grammar;

import ic7cc.ovchinnikov.compiler.ast.impl.ASTNode;
import java_cup.runtime.Symbol;

public class DefaultProductionRule implements ProductionRule {

	private ProductionRuleType type;
	private ASTNode[] syntaxNodes;
	private Symbol[] symbols;
	private ASTNode resultingNode;

	public DefaultProductionRule(ProductionRuleType type, ASTNode resultingNode, ASTNode[] syntaxNodes, Symbol[] symbols) {
		this.type = type;
		this.resultingNode = resultingNode;
		this.syntaxNodes = syntaxNodes;
		this.symbols = symbols;
		
	}
	
	@Override
	public ProductionRuleType getType() {
		return type;
	}

	@Override
	public ASTNode[] getReducedNodes() {
		return syntaxNodes;
	}

	@Override
	public Symbol[] getSymbols() {
		return symbols;
	}

	@Override
	public ASTNode getResultingNode() {
		return resultingNode;
	}

}
