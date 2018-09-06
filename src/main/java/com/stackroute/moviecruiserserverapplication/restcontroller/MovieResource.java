package com.stackroute.moviecruiserserverapplication.restcontroller;

public class MovieResource {

	private final long movieId;
	private final String movieName;
	private final String methodCalled;

	public MovieResource(long movieId, String movieName, String methodCalled) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.methodCalled=methodCalled;
		
	}

	public long getMovieId() {
		return movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getMethodCalled() {
		return methodCalled;
	}

}
