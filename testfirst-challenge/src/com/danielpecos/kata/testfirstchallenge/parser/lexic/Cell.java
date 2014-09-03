package com.danielpecos.kata.testfirstchallenge.parser.lexic;

import com.danielpecos.kata.testfirstchallenge.Sheet;


public class Cell extends Token {
	private String cell = null;
	private Sheet sheet;

	public Cell(Sheet sheet, String cell) {
		this.sheet = sheet;
		this.cell = cell;
	}

	@Override
	public String value() {
		return sheet.get(this.cell);
	}

}
