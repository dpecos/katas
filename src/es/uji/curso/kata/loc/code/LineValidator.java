package es.uji.curso.kata.loc.code;


public abstract class LineValidator {
	protected SingleLineCommentDetector singleLineCommentDetector;
	
	public abstract boolean isValidCodeLine(String line, boolean openCPPComment);

}
