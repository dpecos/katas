package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.code.MultiLineCommentDetector;

public class JavaMultilineCommentDetector extends MultiLineCommentDetector {

	@Override
	public boolean isCommentOpened(String line, boolean openCPPComment) {
		if (line.indexOf("/*") >= 0 || openCPPComment) {
			int posOpen=line.lastIndexOf("/*");
			int posClose=line.lastIndexOf("*/");
			if (posOpen >=0) {
				if (posClose > posOpen) {
					return false;
				} else {
					return true;
				}
			} else {
				if (posClose==-1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
