package es.uji.curso.kata.bowling.util;

import java.util.ArrayList;
import java.util.List;

import es.uji.curso.kata.bowling.Frame;

public class BowlingGameParser {

	private static final int TOTAL_NUMBER_FRAMES = 10;

	Frame lastSpare;
	Frame lastStrike;
	Frame currentFrame;
	
	List<Frame> gameFrames;
	
	public BowlingGameParser(String gameFramesString) {
		this.gameFrames = new ArrayList<Frame>();
		
		this.parse(gameFramesString);
	}
	
	private void parse(String gameFramesString) {
		
		for (int position = 0; position<gameFramesString.length(); position++) {
			char currentRoll = gameFramesString.charAt(position);

			if (this.checkLegalRoll(currentRoll)) {

				this.updateScoreForLastSpare(currentRoll);

				this.updateScoreForLastStrike(currentRoll);

				this.updateScoreForCurrentFrame(currentRoll);

			} else {
				throw new IllegalArgumentException("Unknown roll: " + currentRoll + " found in position " + position);
			}
		}
	}
	
	private void updateScoreForLastSpare(char currentRoll) {
		if (this.lastSpare != null) {
			this.lastSpare.setKnockedDownPinsSpare(currentRoll);
			this.lastSpare = null;
		}
	}
	
	private void updateScoreForLastStrike(char currentRoll) {
		if (this.lastStrike != null) {
			this.lastStrike.setKnockedDownPinsStrike(currentRoll);
			this.lastSpare = lastStrike;
			this.lastStrike = null;
		}
	}

	private void updateScoreForCurrentFrame(char currentRoll) {
		if (this.isNewFrame() && !this.isGameFinished()) {
		
			this.addNewFrameToGame();

			this.currentFrame.setKnockedDownPinsFirstTry(currentRoll);

			checkCurentFrameIsStrike();
			
		} else if (!this.isNewFrame()) {
			
			this.currentFrame.setKnockedDownPinsSecondTry(currentRoll);
			
			checkCurrentFrameIsSpare();
			
			this.currentFrame = null;
		}
	}

	private boolean isNewFrame() {
		return this.currentFrame == null;
	}

	private void checkCurrentFrameIsSpare() {
		if (this.currentFrame.isSpare()) {
			this.lastSpare = this.currentFrame;
		}
	}

	private void checkCurentFrameIsStrike() {
		if (this.currentFrame.isStrike()) {
			this.lastStrike = this.currentFrame;
			this.currentFrame = null;
		}
	}

	private void addNewFrameToGame() {
		this.currentFrame = new Frame();
		this.gameFrames.add(this.currentFrame);
	}

	private boolean isGameFinished() {
		return this.gameFrames.size() >= TOTAL_NUMBER_FRAMES;
	}

	private boolean checkLegalRoll(char currentRoll) {
		if (Character.isDigit(currentRoll) || currentRoll == 'X' || currentRoll == '/' || currentRoll == '-') {
			return true;
		} else {
			return false;
		}
	}

	public List<Frame> getGameFrames() {
		return this.gameFrames;
	}

}
