package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.code.MultiLineCommentDetector;

public class RubyCommentDetector extends MultiLineCommentDetector {

	@Override
	public boolean isCommentOpened(String line, boolean openCPPComment) {
		return false;
	}

}
