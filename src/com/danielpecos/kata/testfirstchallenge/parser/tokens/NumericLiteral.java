package com.danielpecos.kata.testfirstchallenge.parser.tokens;

public class NumericLiteral implements Token {
	String value;
	
	public NumericLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String value() {
		return value;
	}

}
