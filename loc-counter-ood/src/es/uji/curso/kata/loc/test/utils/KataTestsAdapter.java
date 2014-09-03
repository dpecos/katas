package es.uji.curso.kata.loc.test.utils;

import es.uji.curso.kata.loc.LOCCounterFactory;
import es.uji.curso.kata.loc.common.LOCCounter;
import es.uji.curso.kata.loc.common.Language;

public class KataTestsAdapter {
	private Language language;


	public KataTestsAdapter(Language language) {
		this.language = language;
	}
	
	
	public int count(String sourceCode) {
		LOCCounter counter = LOCCounterFactory.getLOCCounter(language);
		
		return counter.countLines(sourceCode);
	}
}
