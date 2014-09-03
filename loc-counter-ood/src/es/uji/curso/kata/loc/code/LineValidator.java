package es.uji.curso.kata.loc.code;


public abstract class LineValidator {
	protected CommentDetector singleLineCommentDetector;
	
	public abstract boolean isValidCodeLine(String line, boolean openMultiLineComment);

}
