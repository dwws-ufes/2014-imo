package com.dwws.imo.exceptions;

public class CandidaturaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CandidaturaException () {
		
    }

	public CandidaturaException (String message) {
		super (message);
    }

	public CandidaturaException (Throwable cause) {
		super (cause);
    }

	public CandidaturaException (String message, Throwable cause) {
		super (message, cause);
    }

}
