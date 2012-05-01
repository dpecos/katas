package com.danielpecos.kata.testfirstchallenge.parser.expressions;

import com.danielpecos.kata.testfirstchallenge.parser.tokens.NumericLiteral;


public class LiteralExpression extends Expression {

	private NumericLiteral numericLiteral;
	
	public LiteralExpression(NumericLiteral numericLiteral) {
		this.numericLiteral = numericLiteral;
	}

	@Override
	public int eval() {
		return Integer.parseInt(this.numericLiteral.value());
	}

}
