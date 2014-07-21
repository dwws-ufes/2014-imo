package com.dwws.imo.exceptions;

public class EntidadeNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException () {
		
    }

	public EntidadeNaoEncontradaException (String message) {
		super (message);
    }

	public EntidadeNaoEncontradaException (Throwable cause) {
		super (cause);
    }

	public EntidadeNaoEncontradaException (String message, Throwable cause) {
		super (message, cause);
    }
}
