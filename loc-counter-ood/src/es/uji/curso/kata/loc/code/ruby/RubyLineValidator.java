package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.code.LineValidator;

public class RubyLineValidator extends LineValidator {
	
	public RubyLineValidator() {
		this.singleLineCommentDetector = new RubySingleLineCommentDetector();
	}

	@Override
	public boolean isValidCodeLine(String line, boolean openMultiLineComment) {
		boolean isCommentLine = singleLineCommentDetector.checkComment(line);
		if (isCommentLine || openMultiLineComment) {
			return false;
		} else {
			return !line.trim().isEmpty();
		}
	}
}
