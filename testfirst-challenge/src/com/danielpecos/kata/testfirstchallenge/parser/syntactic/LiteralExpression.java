package com.danielpecos.kata.testfirstchallenge.parser.syntactic;

import com.danielpecos.kata.testfirstchallenge.parser.lexic.NumericLiteral;


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
