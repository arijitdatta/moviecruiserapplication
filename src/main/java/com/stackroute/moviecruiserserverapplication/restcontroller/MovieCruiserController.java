package com.stackroute.moviecruiserserverapplication.restcontroller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

@CrossOrigin
// @CrossOrigin(origins="http://localhost:4200", maxAge=3600)
@RestController
@RequestMapping("/api/movie")
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

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie fetchedMovie=movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;

		try {
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@PutMapping(path = "{id}")
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

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("Movie deleted successfully",HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

}
