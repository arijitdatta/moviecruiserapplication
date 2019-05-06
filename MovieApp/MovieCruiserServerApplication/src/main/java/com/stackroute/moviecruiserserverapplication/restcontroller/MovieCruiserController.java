package com.stackroute.moviecruiserserverapplication.restcontroller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Jwts;

@RestController
@EnableWebMvc
@CrossOrigin("*")
@RequestMapping("/api/v1/movieservice")
public class MovieCruiserController {

	private MovieService movieService;

	@Autowired
	public MovieCruiserController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@GetMapping
	public ResponseEntity<List<Movie>> fetchAllMovies() {
		return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}

	@GetMapping(path = "/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie fetchedMovie = movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody final Movie movie, HttpServletRequest request,
			HttpServletResponse response) {

		final String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		try {
			movie.setUserId(userId);

			movieService.saveMovie(movie);

		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);

	}

	@PutMapping(path = "/movie/{id}")
	public ResponseEntity<?> updateMovieById(@PathVariable("id") final Integer id, @RequestBody Movie movie) {
		ResponseEntity<?> responseEntity;

		Movie fetchedMovie;
		try {
			fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	@DeleteMapping(path = "/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("Movie deleted successfully", HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/movies")
	public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest req,
			final ServletResponse res) {
		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId), HttpStatus.OK);
	}

}