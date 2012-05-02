package com.danielpecos.kata.testfirstchallenge;

import java.util.HashMap;
import java.util.Map;

import com.danielpecos.kata.testfirstchallenge.parser.AbstractSyntaxTree;

public class Sheet {
	
	Map<String, String> cells = null;
	
	public Sheet() {
		this.cells = new HashMap<String, String>();
	}

	public String get(String theCell) {
		String literal = this.getLiteral(theCell);
		if (literal.startsWith("=")) {
			Integer value = new AbstractSyntaxTree(literal.substring(1)).eval();
			return value.toString();
		} else {
			return literal;
		}
	}

	public void put(String theCell, String value) {
		if (value.trim().matches("\\d+")) {
			value = value.trim();
		}
		this.cells.put(theCell, value);
	}

	public String getLiteral(String theCell) {
		if (this.cells.containsKey(theCell)) {
			return this.cells.get(theCell);
		} else {
			return "";
		}
	}

}
