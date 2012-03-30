package es.uji.curso.kata.loc.code;

public interface CommentDetector {
	boolean checkComment(String line);
	boolean isInsideComment();
}
