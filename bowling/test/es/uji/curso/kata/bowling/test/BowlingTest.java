package es.uji.curso.kata.bowling.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.uji.curso.kata.bowling.BowlingGame;

public class BowlingTest {
	
	private int calculateBowlingScore(String gameString) {
		BowlingGame game = new BowlingGame(gameString);
		return game.calculateScore();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void unknownRoll() {
		calculateBowlingScore("aabbccddeeffgghh");
	}
	
	@Test
	public void zeroScoreGame() {
		assertEquals(0, calculateBowlingScore("--------------------"));
	}
	
	@Test
	public void oneScoreGame() {
		assertEquals(1, calculateBowlingScore("1-------------------"));
	}
	
	@Test
	public void oneSpare() {
		assertEquals(10, calculateBowlingScore("7/------------------"));
	}
	
	@Test
	public void oneStrike() {
		assertEquals(10, calculateBowlingScore("X------------------"));
	}
	
	@Test
	public void oneSpareAndOnePin() {
		assertEquals(12, calculateBowlingScore("7/1-----------------"));
	}
	
	@Test
	public void oneSpareAndTwoPin() {
		assertEquals(13, calculateBowlingScore("7/11----------------"));
	}
	
	@Test
	public void twoSparesInARow() {
		assertEquals(25, calculateBowlingScore("5/5/----------------"));
	}
	
	@Test
	public void oneSpareAtTheEnd() {
		assertEquals(15, calculateBowlingScore("------------------5/5"));
	}
	
	@Test
	public void oneStrikeAndOnePin() {
		assertEquals(12, calculateBowlingScore("X1-----------------"));
	}
	
	@Test
	public void oneStrikeAndTwoPin() {
		assertEquals(14, calculateBowlingScore("X11----------------"));
	}
	
	@Test
	public void twoStrikesInARow() {
		assertEquals(30, calculateBowlingScore("XX----------------"));
	}
	
	@Test
	public void threeStrikesInARow() {
		assertEquals(60, calculateBowlingScore("XXX--------------"));
	}
	
	@Test
	public void oneStrikeAtTheEnd() {
		assertEquals(20, calculateBowlingScore("------------------X55"));
	}
	
	@Test
	public void perfectGame() {
		assertEquals(300, calculateBowlingScore("XXXXXXXXXXXX"));
	}
	
	@Test
	public void ninetyScoreGameWithoutSpares() {
		assertEquals(90, calculateBowlingScore("9-9-9-9-9-9-9-9-9-9-"));
	}
	
	@Test
	public void onehundredfiftyScoreGameWithSpares() {
		assertEquals(150, calculateBowlingScore("5/5/5/5/5/5/5/5/5/5/5"));
	}
}
