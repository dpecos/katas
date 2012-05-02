package com.danielpecos.kata.testfirstchallenge.parser.syntactic;

import com.danielpecos.kata.testfirstchallenge.parser.lexic.Operator;

public class OperatorExpression extends Expression {
	private Operator operator;
	private Expression leftOp;
	private Expression rightOp;

	public OperatorExpression(Operator operator, Expression leftOp, Expression rightOp) {
		this.operator = operator;
		this.leftOp = leftOp;
		this.rightOp = rightOp;
	}

	@Override
	public int eval() {
		if (this.operator != null) {
			if (this.operator.value().equalsIgnoreCase("*")) {
				return this.leftOp.eval() * this.rightOp.eval();
			} else if (this.operator.value().equalsIgnoreCase("+")) {
					return this.leftOp.eval() + this.rightOp.eval();				
			} else {
				return 0;
			}
		} else {
			return this.leftOp.eval();
		}
		
	}
}
