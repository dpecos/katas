package es.uji.curso.kata.loc;

import es.uji.curso.kata.loc.code.java.JavaLOCCounter;
import es.uji.curso.kata.loc.code.ruby.RubyLOCCounter;
import es.uji.curso.kata.loc.common.LOCCounter;
import es.uji.curso.kata.loc.common.Language;

public class LOCCounterFactory {

	public static LOCCounter getLOCCounter(Language language) {
		LOCCounter counter = null;
		switch(language) {
		case Java: 
			counter = new JavaLOCCounter();
			break;

		case Ruby:
			counter = new RubyLOCCounter();
			break;
		}

		return counter;
	}
}
