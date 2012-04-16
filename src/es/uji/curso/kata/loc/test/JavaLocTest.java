package es.uji.curso.kata.loc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.kata.loc.common.Language;
import es.uji.curso.kata.loc.test.utils.KataTestsAdapter;


public class JavaLocTest {
	KataTestsAdapter counter = null;
	
	@Before
	public void setUp() {
		counter = new KataTestsAdapter(Language.Java);
	}
	
	@Test
	public void zeroWhenEmptyString() {
		int actual = counter.count("");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenBlankString() {
		int actual = counter.count("        ");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenTwoEmptyString() {
		int actual = counter.count("\n");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenManyEmptyString() {
		int actual = counter.count("\n   \n\n\n  \n");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenJustALine() {
		int actual = counter.count("aLineOfCode");
		assertEquals(1, actual);
	}
	
	@Test
	public void twoWhenTwoLines() {
		int actual = counter.count("aLineOfCode \n anotherLineOfCode");
		assertEquals(2, actual);
	}
	
	@Test
	public void twoWhenThreeLinesAndOneEmpty() {
		int actual = counter.count("aLineOfCode \n \n anotherLineOfCode");
		assertEquals(2, actual);
	}
	
	@Test
	public void zeroWhenACommentedLine() {
		int actual = counter.count("// comentario");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenACommentedLineCPP() {
		int actual = counter.count("/* comentario */");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenLineWithCommentCPP() {
		int actual = counter.count("code /* comentario */");
		assertEquals(1, actual);
	}
	
	@Test
	public void oneWhenLineWithCommentAtBeginningCPP() {
		int actual = counter.count("/* comentario */ code");
		assertEquals(1, actual);
	}
	
	@Test
	public void oneWhenLineWithCommentAtBeginningEndCPP() {
		int actual = counter.count("code /* comentario */ code");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenLineWithCommentAtBeginningEndCPPWithEmptyStrings() {
		int actual = counter.count("  /* comentario */  ");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenLineWithIncorrectCommentCPP() {
		int actual = counter.count("comentario */");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenLineWithUnclosedCommentCPP() {
		int actual = counter.count("/* comentario");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenTwoLineWithCommentCPP() {
		int actual = counter.count("/* \n */");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenTwoLineWithCommentCPPandSthingElse() {
		int actual = counter.count("/* \n */blahblah");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenTwoLineWithCommentCPPandBlank() {
		int actual = counter.count("/* \n */   ");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenTwoLineWithCommentCPPandSthingElseAndNL() {
		int actual = counter.count("/* \n */blahblah\n");
		assertEquals(1, actual);
	}
	
	@Test
	public void twoWhenTwoLineWithCommentCPPandSthingElseAndNLAndSthingElse() {
		int actual = counter.count("/* \n */blahblah\nmuuuuu");
		assertEquals(2, actual);
	}
	@Test
	public void twoWhenTwoLineWithCommentCPPandSthingElseAndNLAndSthingElseAndCloseComment() {
		int actual = counter.count("/* \n */blahblah\nmuuuuu */");
		assertEquals(2, actual);
	}
	@Test
	public void oneWhenOneLineCommentCPPAndAnotherLine(){
		int actual = counter.count("/* */\nblahblah");
		assertEquals(1, actual);
	}
	@Test
	public void oneWhenOneLineWithTwoCommentCPP(){
		int actual = counter.count("/* \n */blahblah/*\nmuuuuu */");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenMultipleCommentCPP(){
		int actual = counter.count("/* *//* */");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenNestedCommentCPP(){
		int actual = counter.count("/* /* */ */");
		assertEquals(1, actual);
	} 
	
	@Test
	public void oneWhenNestedCommentCPPAndNL(){
		int actual = counter.count("/* /* \n */ */");
		assertEquals(1, actual);
	}
}
