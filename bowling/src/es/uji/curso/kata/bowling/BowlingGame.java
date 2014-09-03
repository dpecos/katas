package es.uji.curso.kata.bowling;

import java.util.List;

import es.uji.curso.kata.bowling.util.BowlingGameParser;

public class BowlingGame {

	List<Frame> gameFrames;

	public BowlingGame(String gameFramesString) {
		BowlingGameParser parser = new BowlingGameParser(gameFramesString);
		this.gameFrames = parser.getGameFrames();
	}

	public int calculateScore() {
		int score = 0;
		for (Frame frame: this.gameFrames) {
			score += frame.getScore();
		}
		return score;
	}

}
