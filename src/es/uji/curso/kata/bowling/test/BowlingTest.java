package es.uji.curso.kata.bowling.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.kata.bowling.BowlingGame;


public class BowlingTest {
	
	@Before
	public void setUp() {
	}

	@Test(expected=IllegalArgumentException.class)
	public void unknownRoll() {
		BowlingGame game = new BowlingGame("aabbccddeeffgghh");
	}
	
	@Test
	public void zeroScoreGame() {
		BowlingGame game = new BowlingGame("--------------------");
		int actual = game.calculateScore();
		assertEquals(0, actual);
	}
	
	@Test
	public void oneScoreGame() {
		BowlingGame game = new BowlingGame("1-------------------");
		int actual = game.calculateScore();
		assertEquals(1, actual);
	}
	
	@Test
	public void oneSpare() {
		BowlingGame game = new BowlingGame("7/------------------");
		int actual = game.calculateScore();
		assertEquals(10, actual);
	}
	
	@Test
	public void oneStrike() {
		BowlingGame game = new BowlingGame("X------------------");
		int actual = game.calculateScore();
		assertEquals(10, actual);
	}
	
	@Test
	public void oneSpareAndOnePin() {
		BowlingGame game = new BowlingGame("7/1-----------------");
		int actual = game.calculateScore();
		assertEquals(12, actual);
	}
	
	@Test
	public void oneSpareAndTwoPin() {
		BowlingGame game = new BowlingGame("7/11----------------");
		int actual = game.calculateScore();
		assertEquals(13, actual);
	}
	
	@Test
	public void twoSparesInARow() {
		BowlingGame game = new BowlingGame("5/5/----------------");
		int actual = game.calculateScore();
		assertEquals(25, actual);
	}
	
	@Test
	public void oneSpareAtTheEnd() {
		BowlingGame game = new BowlingGame("------------------5/5");
		int actual = game.calculateScore();
		assertEquals(15, actual);
	}
	
	@Test
	public void oneStrikeAndOnePin() {
		BowlingGame game = new BowlingGame("X1-----------------");
		int actual = game.calculateScore();
		assertEquals(12, actual);
	}
	
	@Test
	public void oneStrikeAndTwoPin() {
		BowlingGame game = new BowlingGame("X11----------------");
		int actual = game.calculateScore();
		assertEquals(14, actual);
	}
	
	@Test
	public void twoStrikesInARow() {
		BowlingGame game = new BowlingGame("XX----------------");
		int actual = game.calculateScore();
		assertEquals(30, actual);
	}
	
	@Test
	public void threeStrikesInARow() {
		BowlingGame game = new BowlingGame("XXX--------------");
		int actual = game.calculateScore();
		assertEquals(60, actual);
	}
	
	@Test
	public void oneStrikeAtTheEnd() {
		BowlingGame game = new BowlingGame("------------------X55");
		int actual = game.calculateScore();
		assertEquals(20, actual);
	}
	
	@Test
	public void perfectGame() {
		BowlingGame game = new BowlingGame("XXXXXXXXXXXX");
		int actual = game.calculateScore();
		assertEquals(300, actual);
	}
	
	@Test
	public void ninetyScoreGameWithoutSpares() {
		BowlingGame game = new BowlingGame("9-9-9-9-9-9-9-9-9-9-");
		int actual = game.calculateScore();
		assertEquals(90, actual);
	}
	
	@Test
	public void onehundredfiftyScoreGameWithSpares() {
		BowlingGame game = new BowlingGame("5/5/5/5/5/5/5/5/5/5/5");
		int actual = game.calculateScore();
		assertEquals(150, actual);
	}
}
