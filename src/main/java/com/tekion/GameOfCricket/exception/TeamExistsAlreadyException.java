package com.tekion.GameOfCricket.exception;

public class TeamExistsAlreadyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TeamExistsAlreadyException(String message) {
		super(message);
	}

}
