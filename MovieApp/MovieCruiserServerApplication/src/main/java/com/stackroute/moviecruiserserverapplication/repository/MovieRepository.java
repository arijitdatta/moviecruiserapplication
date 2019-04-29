package com.stackroute.moviecruiserserverapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	List<Movie> findByUserId(String userId);

}
