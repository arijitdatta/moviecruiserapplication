package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private final transient MovieRepository movieRepo;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		final Optional<Movie> object = movieRepo.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieAlreadyExistsException("Could Not save movie. It already exists in the database");
		}
		
		movieRepo.save(movie);

		return true;
	}

	@Override
	public Movie updateMovie(Movie updatedMovie) throws MovieNotFoundException {
		final Movie movie=movieRepo.findById(updatedMovie.getId()).orElse(null);
		if(null==movie) {
			throw new MovieNotFoundException("Could not delete. Movie not found!");
		}
		movie.setOverview(updatedMovie.getOverview());
		movie.setPoster_path(updatedMovie.getPoster_path());
		movie.setRelease_date(updatedMovie.getRelease_date());
		movie.setTitle(updatedMovie.getTitle());
		
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		final Movie movie=movieRepo.findById(id).orElse(null);
		if(null==movie) {
			throw new MovieNotFoundException("Could not delete. Movie not found!");
		}
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		final Movie movie=movieRepo.findById(id).orElse(null);
		if(null==movie) {
			throw new MovieNotFoundException("Movie not found!");
		}
		return movie;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepo.findByUserId(userId);
	}

}
