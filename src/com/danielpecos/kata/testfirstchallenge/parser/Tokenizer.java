package com.danielpecos.kata.testfirstchallenge.parser;

import com.danielpecos.kata.testfirstchallenge.parser.tokens.ClosingParenthesesOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.MultiplyOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.NumericLiteral;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.OpeningParenthesesOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.SumOperator;
import com.danielpecos.kata.testfirstchallenge.parser.tokens.Token;

public class Tokenizer {

	private String expression;
	private int currentPosition;

	public Tokenizer(String expression) {
		this.expression = expression;
		this.currentPosition = 0;
	}
	
	public Token nextToken() {

		if (currentPosition < expression.length()) {
			char currentChar = expression.charAt(currentPosition);
			currentPosition++;

			switch (currentChar) {
			case '(':
				return new OpeningParenthesesOperator();
			case ')':
				return new ClosingParenthesesOperator();
			case '*':
				return new MultiplyOperator();
			case '+':
				return new SumOperator();
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
		} else {
			return null;
		}
		

	}

}
