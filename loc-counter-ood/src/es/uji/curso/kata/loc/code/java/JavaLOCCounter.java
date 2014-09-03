package es.uji.curso.kata.loc.code.java;

import es.uji.curso.kata.loc.common.LOCCounter;

public class JavaLOCCounter extends LOCCounter {

	public JavaLOCCounter() {
		super(new JavaLineValidator(), new JavaMultilineCommentDetector());
	}	

}
