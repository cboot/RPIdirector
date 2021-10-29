package com.cboot.rpidirector.services.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4671271154750670397L;

	public EntityAlreadyExistsException(String msg) {
		super(msg);
	}
}
