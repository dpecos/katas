package com.danielpecos.kata.testfirstchallenge;

public class NumericExpressionParser {

	int position;

	public NumericExpressionParser() {
		this.position = 0;
	}
	
	public NumericExpressionParser(int position) {
		this.position = position;
	}

	public Integer parse(String expression) {
		String value = "";

		while (position < expression.length()) {
			char currentChar = expression.charAt(position);
			position++;
			
			int intValue = 0;

			switch (currentChar) {
			case '(':
				return parse(expression);
			case ')':
				return value.equalsIgnoreCase("") ? 0 : Integer.parseInt(value);
			case '*':
				intValue = Integer.parseInt(value) * new NumericExpressionParser(position).parse(expression);
				return intValue;
			case '+':
				intValue = Integer.parseInt(value) + new NumericExpressionParser(position).parse(expression);
				return intValue;				
			default:
				do {
					value += Character.toString(currentChar);
					if (position < expression.length()) {
						currentChar = expression.charAt(position);
						position++;
					} else {
						currentChar = ' ';
					}
				} while (Character.toString(currentChar).matches("\\d") && position < expression.length());

				if (position < expression.length()) {
					position--;
				}
			}
		}

		return Integer.parseInt(value);
	}

}
