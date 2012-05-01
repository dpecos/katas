package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.parser.expressions.Expression;

public class AST {
	private SyntaxTree tree;
	
	public AST(String expression) {
		Tokenizer tokenizer = new Tokenizer(expression);
		this.tree = new SyntaxTree(tokenizer);
	}

	public int eval() {
		Expression expression = tree.build();
		return expression.eval();
	}

}
