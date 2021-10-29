package com.cboot.rpidirector.services.exceptions;

public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 7867766083553465684L;

	public TokenException( String msg ) {
		super(msg);
	}
}
