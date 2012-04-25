/**
 * 
 */
package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.code.CommentDetector;

/**
 * @author Miquel
 *
 */
public class RubyMultiLineCommentDetector implements CommentDetector {

	public static final String RUBY_MULTILINE_COMMENT_BEGIN = "=begin";
	private boolean isMultiLineCommentOpen;

	/* (non-Javadoc)
	 * @see es.uji.curso.kata.loc.code.CommentDetector#checkComment(java.lang.String)
	 */
	//TODO Si no se cierra seguimos en comentario
	@Override
	public boolean checkComment(String line) {
		if (isMultiLineCommentOpen) {
			isMultiLineCommentOpen = !line.trim().equals("=end");
		} else { 
			isMultiLineCommentOpen = line.trim().equals(RubyMultiLineCommentDetector.RUBY_MULTILINE_COMMENT_BEGIN);
		}
		return isMultiLineCommentOpen;
	}

	/* (non-Javadoc)
	 * @see es.uji.curso.kata.loc.code.CommentDetector#isInsideComment()
	 */
	@Override
	public boolean isInsideComment() {
		return isMultiLineCommentOpen;
	}

}
