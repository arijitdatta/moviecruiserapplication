package com.stackroute.moviecruiserserverapplication.repositories;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class MovieRepoTest {

	@Autowired
	private transient MovieRepository repo;
	
	private transient static MovieRepository tearDownRepo;
	
	@PostConstruct
	private void init() {
		tearDownRepo=this.repo;
		deleteAllFromMovieTable();
	}
	
	

	public void setRepo(MovieRepository repo) {
		this.repo = repo;
	}
	
	
	@Test
	public void testSaveMovie() throws Exception{
		repo.save(new Movie(1, "superman", "good movie", "www.abc.com", "30/03/2019"));
		final Movie movie=repo.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}
	
	@Test
	public void testUpdateMovie() throws Exception{
		repo.save(new Movie(1, "superman", "good movie", "www.abc.com" , "30/03/2019"));
		final Movie movie=repo.getOne(1);
		assertEquals(movie.getTitle(), "superman");
		movie.setOverview("hi");
		repo.save(movie);
		final Movie tempMovie=repo.getOne(1);
		assertEquals("hi", tempMovie.getOverview());
		
	}
	
	@Test
	public void testDeleteMovie()throws Exception {
		repo.save(new Movie(1, "superman", "good movie", "www.abc.com", "30/03/2019"));
		final Movie movie=repo.getOne(1);
		assertEquals(movie.getTitle(), "superman");
		repo.delete(movie);
		assertEquals(Optional.empty(), repo.findById(1));
	}
	
	@Test
	public void testGetMovie()throws Exception {
		repo.save(new Movie(1, "superman", "good movie", "www.abc.com", "30/03/2019"));
		final Movie movie=repo.getOne(1);
		assertEquals(movie.getTitle(), "superman");
	}
	

	@Test
	public void testGetAllMovie()throws Exception {
		repo.save(new Movie(1, "superman", "good movie", "www.abc.com", "30/03/2019"));
		repo.save(new Movie(2, "spiderman", "wonderful movie", "www.abc.com", "30/03/2019"));
		final List<Movie> movies=repo.findAll();
		assertEquals(2, movies.size());		
	}
	
	@AfterClass
	public static void doTearDown()throws Exception {
		deleteAllFromMovieTable();
	}

	private static void deleteAllFromMovieTable() {
		tearDownRepo.deleteAll();
		
	}
	
	
	
	
	
	
}
