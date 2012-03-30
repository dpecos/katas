package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.SingleLineCommentDetector;

public class JavaSingleLineCommentDetector implements SingleLineCommentDetector {

	public boolean isSingleLineComment(String line) {
		int posOpen = line.indexOf("/*");
		int posClose = line.indexOf("*/");
		int lastPosClose = line.lastIndexOf("*/");
		if (posOpen == 0) {
			if (lastPosClose == line.length() - 2 || lastPosClose == -1) {
				if (posClose == lastPosClose) {
					return true;
				} else {
					return isSingleLineComment(line.substring(posClose+2));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
