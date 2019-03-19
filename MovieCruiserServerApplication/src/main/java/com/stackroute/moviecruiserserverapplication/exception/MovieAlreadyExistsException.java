package com.stackroute.moviecruiserserverapplication.exception;

public class MovieAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 242780472306522520L;

	private String message;

	@Override
	public String toString() {
		return "MovieAlreadyExistsException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

}
