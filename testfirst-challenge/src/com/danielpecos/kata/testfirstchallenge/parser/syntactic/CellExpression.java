package com.danielpecos.kata.testfirstchallenge.parser.syntactic;

import com.danielpecos.kata.testfirstchallenge.parser.lexic.Cell;


public class CellExpression extends Expression {

	private Cell cell;

	public CellExpression(Cell cell) {
		this.cell = cell;
	}

	@Override
	public int eval() {
		return Integer.parseInt(this.cell.value());
	}

}
