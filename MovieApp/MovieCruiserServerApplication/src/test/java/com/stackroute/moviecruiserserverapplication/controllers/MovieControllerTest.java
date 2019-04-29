package com.stackroute.moviecruiserserverapplication.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.restcontroller.MovieCruiserController;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieCruiserController.class)
public class MovieControllerTest {
	
	private MockMvc movieMockMvc;
	
	@MockBean
	private MovieService movieService;
	
	@InjectMocks
	private MovieCruiserController movieController;
	
	private Movie movie;
	static List<Movie> movies;
	
	@Before 
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"MovieID1", "superman", "man of steel", "www.abc.com", "30/03/2019", "165171");
		movieMockMvc=MockMvcBuilders.standaloneSetup(movieController).build();
		movies=new ArrayList<>();
		movie = new Movie(2,"MovieID2", "batman", "batman begins", "www.abc.com", "30/03/2019", "165171");
		movies.add(movie);
		movie = new Movie(3,"MovieID3", "spiderman", "spidey returns", "www.abc.com", "30/03/2019", "165171");
		movies.add(movie);
		
	}
	
	@Test
	public void testSaveNewMovie() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjUxNzEiLCJpYXQiOjE1NTY1NjYxNTh9.6o2LR7KXbrfXmjEQbOJ3wNT8SPCKOVPA3lirOwnDPH4";
		when(movieService.saveMovie(movie)).thenReturn(true);
		movieMockMvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated()).andDo(print());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		
	}
	
	@Test
	public void testDeleteMovieById() throws Exception {
		when(movieService.deleteMovieById(1)).thenReturn(true);
		movieMockMvc.perform(delete("/api/v1/movieservice/movie/{id}",1 )).andExpect(status().isOk());
		verify(movieService,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
		
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjUxNzEiLCJpYXQiOjE1NTY1NjYxNTh9.6o2LR7KXbrfXmjEQbOJ3wNT8SPCKOVPA3lirOwnDPH4";
		when(movieService.getMyMovies("165171")).thenReturn(movies);
		movieMockMvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(movieService, times(1)).getMyMovies("165171");
		verifyNoMoreInteractions(movieService);
	}
	
	private static String jsonToString(final Object obj) {
		String result;
		try {
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e) {
			result="Json processing exception";
		}
		return result;
	}
	
	
	

}
