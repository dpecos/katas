package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.CommentDetector;


public class JavaMultilineCommentDetector implements CommentDetector {
	public static final String END_MULTILINE_COMMENT = "*/";
	public static final String BEGIN_MULTILINE_COMMENT = "/*";
	
	private boolean isInsideMultilineComment;
	
	@Override
	public boolean checkComment(String line) {
		if (line.indexOf(BEGIN_MULTILINE_COMMENT) >= 0 || isInsideMultilineComment) {
			int openingPosition=line.lastIndexOf(BEGIN_MULTILINE_COMMENT);
			int closingPosition=line.lastIndexOf(END_MULTILINE_COMMENT);
			isInsideMultilineComment=isCommentClosed(openingPosition, closingPosition);
		} else {
			isInsideMultilineComment = false;
		}
		return isInsideMultilineComment;
	}

	/**
	 * @param openingPosition
	 * @param closingPosition
	 * @return 
	 */
	private boolean isCommentClosed(int openingPosition, int closingPosition) {
		if (openingPosition >=0) {
			if (closingPosition > openingPosition) {
				return false;
			} else {
				return true;
			}
		} else {
			if (closingPosition==-1) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean isInsideComment() {
		return isInsideMultilineComment;
	}

}
