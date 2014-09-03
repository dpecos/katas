package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.common.LOCCounter;

public class RubyLOCCounter extends LOCCounter {

	public RubyLOCCounter() {
		super(new RubyLineValidator(), new RubyMultiLineCommentDetector());
	}

}
