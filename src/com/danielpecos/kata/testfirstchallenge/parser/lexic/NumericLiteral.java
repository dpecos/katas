package com.danielpecos.kata.testfirstchallenge.parser.lexic;

public class NumericLiteral extends Token {
	String value;
	
	public NumericLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String value() {
		return value;
	}

}
