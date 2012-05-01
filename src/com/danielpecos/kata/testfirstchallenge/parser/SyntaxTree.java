package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.parser.expressions.Expression;
import com.danielpecos.kata.testfirstchallenge.parser.expressions.LiteralExpression;
import com.danielpecos.kata.testfirstchallenge.parser.expressions.OperatorExpression;
import com.danielpecos.kata.testfirstchallenge.parser.expressions.ParenthesizedExpression;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.ClosingParenthesesOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.MultiplyOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.NumericLiteral;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.OpeningParenthesesOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.Operator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.Token;

public class SyntaxTree {

	private Tokenizer tokenizer;

	public SyntaxTree(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public Expression build() {
		Expression expr = null;

		Token token = null;
		do {
			token = tokenizer.nextToken();
			if (token != null) {
				if (token instanceof NumericLiteral) {
					expr = new LiteralExpression((NumericLiteral)token);
				} else if (token instanceof OpeningParenthesesOperator) {
					expr = new ParenthesizedExpression(build());
				} else if (token instanceof ClosingParenthesesOperator) {
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
			} else if (token instanceof OpeningParenthesesOperator) {
				expr = new ParenthesizedExpression(build());
			} else if (token instanceof ClosingParenthesesOperator) {
				return expr;
			}
		}

		return expr;
	}

}
