package com.stackroute.moviecruiserserverapplication.restcontroller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieCruiserController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/getmovie")
	public MovieResource getMovieById(@RequestParam(value = "movieid", required = true) String movieId) {
		return new MovieResource(counter.incrementAndGet(), String.format(template, movieId), "GET");
	}

	@RequestMapping(value = "/savemovie", method = RequestMethod.POST)
	public MovieResource saveMovie(@RequestParam(value = "name", required = true) String name) {
		return new MovieResource(counter.incrementAndGet(), String.format(template, name), "POST");
	}

	@RequestMapping(value = "/updatemoviebyid", method = RequestMethod.PATCH)
	public MovieResource updateMovieByID(@RequestParam(value = "movieId", required = true) String movieId) {
		return new MovieResource(counter.incrementAndGet(), String.format(template, movieId), "PATCH");
	}

	@RequestMapping(value = "/deletemoviebyid", method = RequestMethod.DELETE)
	public MovieResource deleteMovieByID(@RequestParam(value = "movieId", required = true) String movieId) {
		return new MovieResource(counter.incrementAndGet(), String.format(template, movieId), "DELETE");
	}

}
