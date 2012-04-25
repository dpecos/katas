package es.uji.curso.kata.loc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.kata.loc.common.Language;
import es.uji.curso.kata.loc.test.utils.KataTestsFacade;


public class RubyLocTest {
	KataTestsFacade counter = null;
	
	@Before
	public void setUp() {
		counter = new KataTestsFacade(Language.Ruby);
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
		int actual = counter.count("# comentario");
		assertEquals(0, actual);
	}
	
	@Test
	public void zeroWhenACommentedLineCPP() {
		int actual = counter.count("=begin\n comentario \n=end");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenIncorrectCommentCPP() {
		int actual = counter.count("code =begin comentario =end");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenIncorrectClosingComment() {
		int actual = counter.count("=begin\n comentario =end");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenIncorrectClosingComment() {
		int actual = counter.count("=begin\n comentario\n =end aaa");
		assertEquals(0, actual);
	}
	
	@Test
	public void twoWhenCommentCPP() {
		int actual = counter.count("code\n=begin\n comentario\n=end\n code");
		assertEquals(2, actual);
	}
	
	@Test
	public void zeroWhenLineWithCommentAtBeginningEndCPPWithEmptyStrings() {
		int actual = counter.count("   \n=begin\n comentario \n=end\n   ");
		assertEquals(0, actual);
	}
	
	
	@Test
	public void zeroWhenTwoLineWithCommentCPP() {
		int actual = counter.count("\n=begin\n comentario \n=end\n");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenTwoLineWithCommentCPPandSthingElseAndNL() {
		int actual = counter.count("=begin\n=end\nblahblah\n");
		assertEquals(1, actual);
	}
	
	@Test
	public void twoWhenTwoLineWithCommentCPPandSthingElseAndNLAndSthingElse() {
		int actual = counter.count("=begin\n=end\nblahblah\nmuuuuu");
		assertEquals(2, actual);
	}
	@Test
	public void twoWhenTwoLineWithCommentCPPandSthingElseAndNLAndSthingElseAndCloseComment() {
		int actual = counter.count("=begin\n=end\nblahblah\nmuuuuu\n=end");
		assertEquals(3, actual);
	}
	@Test
	public void oneWhenOneLineCommentCPPAndAnotherLine(){
		int actual = counter.count("=begin\n=end\nblahblah");
		assertEquals(1, actual);
	}
	@Test
	public void oneWhenOneLineWithTwoCommentCPP(){
		int actual = counter.count("=begin\n=end\nblahblah\n=begin\nmuuuuu\n=end");
		assertEquals(1, actual);
	}
	
	@Test
	public void zeroWhenMultipleCommentCPP(){
		int actual = counter.count("=begin\n=end\n=begin\n=end\n");
		assertEquals(0, actual);
	}
	
	@Test
	public void oneWhenNestedCommentCPP(){
		int actual = counter.count("=begin\n=begin\n=end\n=end");
		assertEquals(1, actual);
	} 
	
	@Test
	public void oneWhenNestedCommentCPPAndNL(){
		int actual = counter.count("=begin\n=begin\n\n=end\n=end");
		assertEquals(1, actual);
	}
}
