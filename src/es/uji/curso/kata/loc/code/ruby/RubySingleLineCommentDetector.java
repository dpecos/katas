package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.code.CommentDetector;

public class RubySingleLineCommentDetector implements CommentDetector {

	private static final String RUBY_SINGLELINE_COMMENT_BEGIN = "#";

	@Override
	public boolean checkComment(String line) {
		if (line.startsWith(RubySingleLineCommentDetector.RUBY_SINGLELINE_COMMENT_BEGIN) ||
				line.trim().equals(RubyMultiLineCommentDetector.RUBY_MULTILINE_COMMENT_BEGIN)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isInsideComment() {
		// TODO Auto-generated method stub
		return false;
	}

}
