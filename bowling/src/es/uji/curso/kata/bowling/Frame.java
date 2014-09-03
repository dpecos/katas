package es.uji.curso.kata.bowling;

public class Frame {
	private static final int TOTAL_NUMBER_PINS = 10;
	private static final int NUMBER_POSSIBLE_TRIES_FRAME = 4;
	
	private static final char STRIKE_CHAR = 'X';
	private static final char SPARE_CHAR = '/';
	
	private static final int FIRST_TRY = 0;
	private static final int SECOND_TRY = 1;
	private static final int SPARE_PINS = 2;
	private static final int STRIKE_PINS = 3;

	private int[] pinsPerTry;
	
	public Frame() {
		this.pinsPerTry = new int[NUMBER_POSSIBLE_TRIES_FRAME];
	}
	
	public void setKnockedDownPinsFirstTry(char roll) {
		this.pinsPerTry[FIRST_TRY] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsSecondTry(char roll) {
		this.pinsPerTry[SECOND_TRY] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsSpare(char roll) {
		this.pinsPerTry[SPARE_PINS] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsStrike(char roll) {
		this.pinsPerTry[STRIKE_PINS] = this.getNumberOfPins(roll);
	}

	private int getNumberOfPins(char roll) {
		int pins = 0;
		if (Character.isDigit(roll)) {
			pins = Integer.parseInt(Character.toString(roll));
		} else if (roll == SPARE_CHAR) {
			pins = TOTAL_NUMBER_PINS - this.pinsPerTry[FIRST_TRY];
		} else if (roll == STRIKE_CHAR) {
			pins = TOTAL_NUMBER_PINS;
		}
		return pins;
	}

	private boolean isAllPinsKnockedDown() {
		return this.pinsPerTry[0] + this.pinsPerTry[SECOND_TRY] == TOTAL_NUMBER_PINS;
	}
	
	public boolean isSpare() {
		return isAllPinsKnockedDown() && !isStrike();
	}
	
	public boolean isStrike() {
		return this.pinsPerTry[FIRST_TRY] == TOTAL_NUMBER_PINS;
	}
	
	public int getScore() {
		return this.pinsPerTry[FIRST_TRY] + this.pinsPerTry[SECOND_TRY] + this.pinsPerTry[SPARE_PINS] + this.pinsPerTry[STRIKE_PINS];
	}
}
