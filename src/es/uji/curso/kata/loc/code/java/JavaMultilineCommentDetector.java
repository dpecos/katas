package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.CommentDetector;


public class JavaMultilineCommentDetector implements CommentDetector {
	public static final String END_MULTILINE_COMMENT = "*/";
	public static final String BEGIN_MULTILINE_COMMENT = "/*";
	
	private boolean isInsideMultilineComment;
	
	@Override
	public boolean checkComment(String line) {
		if (line.indexOf(BEGIN_MULTILINE_COMMENT) >= 0 || isInsideMultilineComment) {
			int posOpen=line.lastIndexOf(BEGIN_MULTILINE_COMMENT);
			int posClose=line.lastIndexOf(END_MULTILINE_COMMENT);
			if (posOpen >=0) {
				if (posClose > posOpen) {
					isInsideMultilineComment = false;
				} else {
					isInsideMultilineComment = true;
				}
			} else {
				if (posClose==-1) {
					isInsideMultilineComment = true;
				} else {
					isInsideMultilineComment = false;
				}
			}
		} else {
			isInsideMultilineComment = false;
		}
		return isInsideMultilineComment;
	}

	@Override
	public boolean isInsideComment() {
		return isInsideMultilineComment;
	}

}
