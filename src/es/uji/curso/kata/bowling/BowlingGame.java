package es.uji.curso.kata.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

	List<Frame> gameFrames;

	public BowlingGame(String gameFramesString) {
		this.gameFrames = new ArrayList<Frame>();
		this.parse(gameFramesString);
	}

	private void parse(String gameFramesString) {
		Frame currentFrame = null;
		Frame lastSpare = null;
		Frame lastStrike = null;

		for (int position = 0; position<gameFramesString.length(); position++) {
			char currentRoll = gameFramesString.charAt(position);

			if (this.checkLegalRoll(currentRoll)) {

				if (lastSpare != null) {
					lastSpare.setKnockedDownPinsSpare(currentRoll);
					lastSpare = null;
				}

				if (lastStrike != null) {
					lastStrike.setKnockedDownPinsStrike(currentRoll);
					lastSpare = lastStrike;
					lastStrike = null;
				}

				if (currentFrame == null && gameFrames.size() < 10) {
					currentFrame = new Frame();
					this.gameFrames.add(currentFrame);

					currentFrame.setKnockedDownPinsFirstTry(currentRoll);

					if (currentFrame.isStrike()) {
						lastStrike = currentFrame;
						currentFrame = null;
					}
				} else if (currentFrame != null) {
					currentFrame.setKnockedDownPinsSecondTry(currentRoll);
					if (currentFrame.isSpare()) {
						lastSpare = currentFrame;
					}
					currentFrame = null;
				}

			} else {
				throw new IllegalArgumentException("Unknown roll: " + currentRoll + " found in position " + position);
			}
		}

	}

	private boolean checkLegalRoll(char currentRoll) {
		if (Character.isDigit(currentRoll) || currentRoll == 'X' || currentRoll == '/' || currentRoll == '-') {
			return true;
		} else {
			return false;
		}
	}

	public int calculateScore() {
		int score = 0;
		for (Frame frame: gameFrames) {
			score += frame.getScore();
		}
		return score;
	}

}
