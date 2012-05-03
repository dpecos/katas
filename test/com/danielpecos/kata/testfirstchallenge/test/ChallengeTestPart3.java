package com.danielpecos.kata.testfirstchallenge.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.danielpecos.kata.testfirstchallenge.Sheet;

public class ChallengeTestPart3 {

	@Test
	public void testThatCellReferenceWorks () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "8");
		sheet.put("A2", "=A1");
		assertEquals("cell lookup", "8", sheet.get("A2"));
	}
	
	@Test
	public void testThatCellChangesPropagate () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "8");
		sheet.put("A2", "=A1");
		assertEquals("cell lookup", "8", sheet.get("A2"));

		sheet.put("A1", "9");
		assertEquals("cell change propagation", "9", sheet.get("A2"));
	}

	@Test
	public void testThatFormulasKnowCellsAndRecalculate () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "8");
		sheet.put("A2", "3");
		sheet.put("B1", "=A1*(A1-A2)+A2/3");
		assertEquals("calculation with cells", "41", sheet.get("B1"));

		sheet.put("A2", "6");
		assertEquals("re-calculation", "18", sheet.get("B1"));
	}
	
	@Test
	public void testThatFormulasKnowCellsAndRecalculateNestedParenthesis () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "8");
		sheet.put("A2", "3");
		sheet.put("B1", "=((A1*(((A1-A2)))+(((A2))/(3))))");
		assertEquals("calculation with cells", "41", sheet.get("B1"));

		sheet.put("A2", "6");
		assertEquals("re-calculation", "18", sheet.get("B1"));
	}

	@Test
	public void testThatDeepPropagationWorks () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "8");
		sheet.put("A2", "=A1");
		sheet.put("A3", "=A2");
		sheet.put("A4", "=A3");
		assertEquals("deep propagation", "8", sheet.get("A4"));

		sheet.put("A2", "6");
		assertEquals("deep re-calculation", "6", sheet.get("A4"));
	}

	// The following test is likely to pass already.
	@Test
	public void testThatFormulaWorksWithManyCells () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "10");
		sheet.put("A2", "=A1+B1");
		sheet.put("A3", "=A2+B2");
		sheet.put("A4", "=A3");
		sheet.put("B1", "7");
		sheet.put("B2", "=A2");
		sheet.put("B3", "=A3-A2");
		sheet.put("B4", "=A4+B3");

		assertEquals("multiple expressions - A4", "34", sheet.get("A4"));
		assertEquals("multiple expressions - B4", "51", sheet.get("B4"));
	}

	// Refactor and get everything nice and clean.

	// Next: (I almost made this a separate part, and when I
	// originally did it, I did it in a different design session).
	// So take a break if you need one.

	// There's one big open issue for formulas: what about
	// circular references? 

	// I'll sketch some hints, but you should define your own tests
	// that drive toward a solution compatible with your own
	// implementation.

	@Test
	public void testThatCircularReferenceDoesntCrash() {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=A1");
		assertTrue(true);
	}

	// Just like errors return a special value, it might be nice
	// if circular references did too. (See notes below).
	@Test
	public void testThatCircularReferencesAdmitIt () {
		Sheet sheet = new Sheet();
		sheet.put("A1", "=A1");
		assertEquals("Detect circularity", "#Circular", sheet.get("A1"));
	}
	
	// You might come up with some other approach that suits your
	// taste. We won't be exploring this corner of the solution
	// any further; you just want a scheme that blocks silly mistakes.
	// Make sure you test deep circularities involving partially
	// evaluated expressions.

	// A hint: if you blindly evaluate an expression you have no
	// control over how deep the expression can be, since
	// circular references appear to be infinitely deep.

	// Where are we? I intend to spend the next two parts hooking
	// up a GUI. Then there will be an optional part that pushes
	// things in an unexpected direction just to get a sense
	// of our software's robustness.

}
