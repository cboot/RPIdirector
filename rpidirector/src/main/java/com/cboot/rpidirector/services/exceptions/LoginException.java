package com.cboot.rpidirector.services.exceptions;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 7867766083553465684L;

	public LoginException( String msg ) {
		super(msg);
	}
}
