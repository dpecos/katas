package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.LineValidator;

public class JavaLineValidator extends LineValidator {
	
	public JavaLineValidator() {
		this.singleLineCommentDetector = new JavaSingleLineCommentDetector();
	}

	@Override
	public boolean isValidCodeLine(String line, boolean openCPPComment) {
		line = line.trim();
		if (openCPPComment) {
			int posCommentClosing =line.indexOf("*/");
			if ( posCommentClosing != -1 && posCommentClosing < line.length() -2 ) {
				return true;
			} else {
				return false;
			}
		} else {
			boolean vacia = line.isEmpty();
			boolean comentario = line.startsWith("//");
			boolean comentarioCPP = singleLineCommentDetector.checkComment(line);
			return !vacia && !comentario && !comentarioCPP;
		}
	}
	
}
