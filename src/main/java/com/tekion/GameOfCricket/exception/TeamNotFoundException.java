package com.tekion.GameOfCricket.exception;

public class TeamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(String message) {
		super(message);
	}

}
