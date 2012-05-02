package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.parser.lexic.ClosingParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.MultiplyOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.NumericLiteral;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.OpeningParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Operator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Token;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.Expression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.LiteralExpression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.OperatorExpression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.ParenthesizedExpression;

public class AbstractSyntaxTree {
	
	private Tokenizer tokenizer;

	public AbstractSyntaxTree(String expression) {
		this.tokenizer = new Tokenizer(expression);
	}

	public int eval() {
		Expression expression = this.build();
		return expression.eval();
	}

	private Expression build() {
		Expression expr = null;

		Token token = null;
		do {
			token = tokenizer.nextToken();
			if (token != null) {
				if (token instanceof NumericLiteral) {
					expr = new LiteralExpression((NumericLiteral)token);
				} else if (token instanceof OpeningParenthesis) {
					expr = new ParenthesizedExpression(build());
				} else if (token instanceof ClosingParenthesis) {
					return expr;
				} else if (token instanceof Operator) {
					Expression leftExpression = expr;
					Token operator = token;
					Expression rightExpression = null;
					if (token instanceof MultiplyOperator) {
						rightExpression = nextExpression();
					} else {
						rightExpression = build();
					}

					expr = new OperatorExpression((Operator)operator, leftExpression, rightExpression);
				}
			}
		} while (token != null);

		return expr;
	}

	private Expression nextExpression() {
		Expression expr = null;

		Token token = tokenizer.nextToken();
		if (token != null) {
			if (token instanceof NumericLiteral) {
				expr = new LiteralExpression((NumericLiteral)token);
			} else if (token instanceof OpeningParenthesis) {
				expr = new ParenthesizedExpression(build());
			} else if (token instanceof ClosingParenthesis) {
				return expr;
			}
		}

		return expr;
	}
}
