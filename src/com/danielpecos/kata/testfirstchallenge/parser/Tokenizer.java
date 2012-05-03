package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.Sheet;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Cell;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.ClosingParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.DivisionOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.MultiplyOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.NumericLiteral;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.OpeningParenthesis;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.SubstractionOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.SumOperator;
import com.danielpecos.kata.testfirstchallenge.parser.lexic.Token;

public class Tokenizer {

	private String expression;
	private int currentPosition;
	private Sheet sheet;
	private Token previousToken;

	public Tokenizer(String expression, Sheet sheet) {
		this.expression = expression;
		this.currentPosition = 0;
		this.sheet = sheet;
	}

	public Token nextToken() {

		if (previousToken != null) {
			Token tmp = previousToken;
			previousToken = null;
			return tmp;
		} else {

			if (currentPosition < expression.length()) {
				char currentChar = expression.charAt(currentPosition);
				currentPosition++;

				if (Character.isLetter(currentChar)) {
					return new Cell(this.sheet, Character.toString(currentChar) + nextToken().value());
				} else {
					switch (currentChar) {
					case '(':
						return new OpeningParenthesis();
					case ')':
						return new ClosingParenthesis();
					case '*':
						return new MultiplyOperator();
					case '/':
						return new DivisionOperator();					
					case '+':
						return new SumOperator();
					case '-':
						return new SubstractionOperator();					
					default:
						String value = "";
						do {
							value += Character.toString(currentChar);
							if (currentPosition < expression.length()) {
								currentChar = expression.charAt(currentPosition);
								currentPosition++;
							} else {
								currentChar = ' ';
							}
						} while (Character.toString(currentChar).matches("\\d") && currentPosition < expression.length());

						if (currentPosition < expression.length()) {
							currentPosition--;
						}

						return new NumericLiteral(value);
					}
				}
			} else {
				return null;
			}

		}

	}

	public void rollbackToken(Token token) {
		this.previousToken = token;
	}

}
