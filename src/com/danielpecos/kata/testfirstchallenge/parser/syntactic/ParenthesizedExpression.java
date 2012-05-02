package com.danielpecos.kata.testfirstchallenge.parser.syntactic;

public class ParenthesizedExpression extends Expression {
	
	private Expression expression;

	public ParenthesizedExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public int eval() {
		return this.expression.eval();
	}

}
