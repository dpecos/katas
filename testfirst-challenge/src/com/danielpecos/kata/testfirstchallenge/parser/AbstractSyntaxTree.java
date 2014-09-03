package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.Sheet;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Cell;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.ClosingParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.DivisionOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.MultiplyOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.NumericLiteral;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.OpeningParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Operator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Token;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.CellExpression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.Expression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.LiteralExpression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.OperatorExpression;
import com.danielpecos.kata.testfirstchallenge.parser.syntactic.ParenthesizedExpression;

public class AbstractSyntaxTree {
	
	private Tokenizer tokenizer;

	public AbstractSyntaxTree(Sheet sheet, String expression) {
		this.tokenizer = new Tokenizer(expression, sheet);
	}

	public int eval() {
		Expression expression = this.build(false);
		return expression.eval();
	}

	private Expression build(boolean returnOnCloseParenthesis) {
		Expression expr = null;

		Token token = null;
		do {
			token = tokenizer.nextToken();
			if (token != null) {
				if (token instanceof NumericLiteral) {
					expr = new LiteralExpression((NumericLiteral)token);
				} else if (token instanceof OpeningParenthesis) {
					expr = new ParenthesizedExpression(build(true));
					// we have to discard the "propagating-closing-parenthesis"
					tokenizer.nextToken(); 
				} else if (token instanceof ClosingParenthesis) {
					if (returnOnCloseParenthesis) {
						tokenizer.rollbackToken(token);
					}
					return expr;
				} else if (token instanceof Operator) {
					Expression leftExpression = expr;
					Token operator = token;
					Expression rightExpression = null;
					if (token instanceof MultiplyOperator || token instanceof DivisionOperator) {
						rightExpression = nextExpression();
					} else {
						rightExpression = build(returnOnCloseParenthesis);
					}
					
					expr = new OperatorExpression((Operator)operator, leftExpression, rightExpression);
				} else if (token instanceof Cell) {
					expr = new CellExpression((Cell)token);
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
				expr = new ParenthesizedExpression(build(true));
				// we have to discard the "propagating-closing-parenthesis"
				tokenizer.nextToken();
			}
		}

		return expr;
	}
}
