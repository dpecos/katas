package es.uji.curso.kata.loc.common;

import es.uji.curso.kata.loc.code.LineValidator;
import es.uji.curso.kata.loc.code.MultiLineCommentDetector;

public abstract class LOCCounter {
	private static final String EOL = "\n";
	
	protected LineValidator lineValidator;
	protected MultiLineCommentDetector commentDetector;
	
	public LOCCounter(LineValidator lineValidator, MultiLineCommentDetector commentDetector) {
		this.lineValidator = lineValidator;
		this.commentDetector = commentDetector;
	}
	
	public int countLines(String sourceCode) {
		int counter = 0;
		String[] lines = sourceCode.split(EOL);
		
		boolean openCPPComment = false;
		
		for(String line : lines) {
			if (lineValidator.isValidCodeLine(line, openCPPComment)) {
				counter ++;
			} 
			openCPPComment = commentDetector.isCommentOpened(line, openCPPComment);
		}
		return counter;
	}
}
