package es.uji.curso.kata.loc;

import javax.swing.text.Position;

public class LOCcounter {

	public int count(String src) {
		int counter = 0;
		String[] lines = src.split("\n");
		boolean openCPPComment=false;
		for(String line : lines) {
			if (validCodeLine(line,openCPPComment)) {
				counter ++;
			} 
			openCPPComment = unclosedCPPComment(line, openCPPComment);
		}
		return counter;
	}

	private boolean unclosedCPPComment(String line, boolean openCPPComment) {
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

	private boolean validCodeLine(String line, boolean openCPPComment) {
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
			boolean comentarioCPP = checkCPPCommentOneLine(line);
			return !vacia && !comentario && !comentarioCPP;
		}
	}

	private boolean checkCPPCommentOneLine(String line) {
		int posOpen = line.indexOf("/*");
		int posClose = line.indexOf("*/");
		int lastPosClose = line.lastIndexOf("*/");
		if (posOpen == 0) {
			if (lastPosClose == line.length() - 2 || lastPosClose == -1) {
				if (posClose == lastPosClose) {
					return true;
				} else {
					return checkCPPCommentOneLine(line.substring(posClose+2));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
