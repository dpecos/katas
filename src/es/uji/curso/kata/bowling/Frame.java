package es.uji.curso.kata.bowling;

public class Frame {
	private static final int TOTAL_NUMBER_PINS = 10;
	
	int[] pinsPerTry;
	
	public Frame() {
		this.pinsPerTry = new int[4];
	}
	
	public void setKnockedDownPinsFirstTry(char roll) {
		this.pinsPerTry[0] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsSecondTry(char roll) {
		this.pinsPerTry[1] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsSpare(char roll) {
		this.pinsPerTry[2] = this.getNumberOfPins(roll);
	}
	
	public void setKnockedDownPinsStrike(char roll) {
		this.pinsPerTry[3] = this.getNumberOfPins(roll);
	}

	private int getNumberOfPins(char roll) {
		int pins = 0;
		if (Character.isDigit(roll)) {
			pins = Integer.parseInt(Character.toString(roll));
		} else if (roll == '/') {
			pins = TOTAL_NUMBER_PINS - this.pinsPerTry[0];
		} else if (roll == 'X') {
			pins = TOTAL_NUMBER_PINS;
		}
		return pins;
	}

	private boolean isAllPinsKnockedDown() {
		return this.pinsPerTry[0] + this.pinsPerTry[1] == TOTAL_NUMBER_PINS;
	}
	
	public boolean isSpare() {
		return isAllPinsKnockedDown() && !isStrike();
	}
	
	public boolean isStrike() {
		return this.pinsPerTry[0] == TOTAL_NUMBER_PINS;
	}
	
	public int getScore() {
		return this.pinsPerTry[0] + this.pinsPerTry[1] + this.pinsPerTry[2] + this.pinsPerTry[3];
	}
}
