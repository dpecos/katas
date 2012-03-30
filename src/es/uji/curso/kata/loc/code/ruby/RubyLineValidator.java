package es.uji.curso.kata.loc.code.ruby;

import es.uji.curso.kata.loc.code.LineValidator;

public class RubyLineValidator extends LineValidator {

	@Override
	public boolean isValidCodeLine(String line, boolean openCPPComment) {
		return false;
	}

}
