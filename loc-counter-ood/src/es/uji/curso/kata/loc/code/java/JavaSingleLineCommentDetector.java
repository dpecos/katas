package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.CommentDetector;

public class JavaSingleLineCommentDetector implements CommentDetector {

	public boolean checkComment(String line) {
		int posOpen = line.indexOf(JavaMultilineCommentDetector.BEGIN_MULTILINE_COMMENT);
		int posClose = line.indexOf(JavaMultilineCommentDetector.END_MULTILINE_COMMENT);
		int lastPosClose = line.lastIndexOf(JavaMultilineCommentDetector.END_MULTILINE_COMMENT);
		if (posOpen == 0) {
			if (lastPosClose == line.length() - 2 || lastPosClose == -1) {
				if (posClose == lastPosClose) {
					return true;
				} else {
					return checkComment(line.substring(posClose+2));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean isInsideComment() {
		return false;
	}
}
