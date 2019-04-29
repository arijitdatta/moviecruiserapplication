package com.stackroute.moviecruiserserverapplication.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
import com.stackroute.moviecruiserserverapplication.service.MovieServiceImpl;

public class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepo;

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	private Movie movie;
	Optional<Movie> options;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"MovieID1", "superman", "good movie", "www.abc.com", "30/03/2019", "Arijit123");
		options = Optional.of(movie);
	}

	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull("jpaRepository creation fails: use @injectMocks on movieServiceImpl", movieRepo);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag=movieServiceImpl.saveMovie(movie);
		assertEquals("saving movie failed, the call to movieDAOImpl is return false, check this method", true, flag);;
		verify(movieRepo, times(1)).save(movie);
		
	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag=movieServiceImpl.saveMovie(movie);

	}
	
	@Test
	public void testUpdateMovie() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setOverview("not so great movie");
		Movie movie1=movieServiceImpl.updateMovie(movie);
		assertEquals("updating movie failed", "not so great movie", movie1.getOverview());
		verify(movieRepo, times(1)).save(movie);
		
	}
	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		boolean flag=movieServiceImpl.deleteMovieById(1);
		assertEquals("deleting movie failed", true, flag);
		verify(movieRepo, times(1)).delete(movie);
		
	}
	
	@Test
	public void testGetMyMovies() throws MovieNotFoundException{
		
		List<Movie> movieList=new ArrayList<Movie>();
		movieList.add(movie);
		when(movieRepo.findByUserId("Arijit123")).thenReturn(movieList);
		List<Movie> movies1=movieServiceImpl.getMyMovies("Arijit123");
		
		assertEquals(movieList, movies1);
		verify(movieRepo, times(1)).findByUserId("Arijit123");
		
	}

}
