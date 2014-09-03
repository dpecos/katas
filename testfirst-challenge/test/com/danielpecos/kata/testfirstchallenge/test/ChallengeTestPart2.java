package com.danielpecos.kata.testfirstchallenge.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.danielpecos.kata.testfirstchallenge.Sheet;


public class ChallengeTestPart2 {

	@Test
	public void testFormulaSpec() {
		Sheet sheet = new Sheet();
		sheet.put("B1", " =7"); // note leading space
		assertEquals("Not a formula", " =7", sheet.get("B1"));
		assertEquals("Unchanged", " =7", sheet.getLiteral("B1"));
	}

	@Test
	public void testConstantFormula() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=7");
		assertEquals("Formula", "=7", sheet.getLiteral("A1"));
		assertEquals("Value", "7", sheet.get("A1"));
	}

	@Test
	public void testParentheses() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=(7)");
		assertEquals("Parends", "7", sheet.get("A1"));
	}

	@Test
	public void testDeepParentheses() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=((((10))))");
		assertEquals("Parends", "10", sheet.get("A1"));
	}

	@Test
	public void testMultiply() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=2*3*4");
		assertEquals("Times", "24", sheet.get("A1"));
	}
	
	@Test
	public void testParenthesesMultiply() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=(3*2)");
		assertEquals("Parends", "6", sheet.get("A1"));
	}
	
	@Test
	public void testParenthesesSum() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=(3+2)");
		assertEquals("Parends", "5", sheet.get("A1"));
	}	

	@Test
	public void testAdd() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=71+2+3");
		assertEquals("Add", "76", sheet.get("A1"));
	}

	@Test
	public void testPrecedence1() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=7+2*3");
		assertEquals("Precedence", "13", sheet.get("A1"));
	}
	
	@Test
	public void testPrecedence2() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=7*2+3");
		assertEquals("Precedence", "17", sheet.get("A1"));
	}

	@Test
	public void testFullExpression() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=7*(2+3)*((((2+1))))");
		assertEquals("Expr", "105", sheet.get("A1"));
	}

}
