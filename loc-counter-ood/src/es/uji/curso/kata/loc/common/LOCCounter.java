package es.uji.curso.kata.loc.common;

import es.uji.curso.kata.loc.code.CommentDetector;
import es.uji.curso.kata.loc.code.LineValidator;

public abstract class LOCCounter {
	private static final String EOL = "\n";
	
	protected LineValidator lineValidator;
	protected CommentDetector commentDetector;
	
	public LOCCounter(LineValidator lineValidator, CommentDetector commentDetector) {
		this.lineValidator = lineValidator;
		this.commentDetector = commentDetector;
	}
	
	public int countLines(String sourceCode) {
		int counter = 0;
		String[] lines = sourceCode.split(EOL);
		
		for(String line : lines) {
			if (lineValidator.isValidCodeLine(line, commentDetector.isInsideComment())) {
				counter ++;
			} 
			commentDetector.checkComment(line);
		}
		return counter;
	}
}
