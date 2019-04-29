package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private int id;
	
	@Column(name = "movie_id")
	private String movieId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "overview", length=3000)
	private String overview;
	
	@Column(name = "poster_path", length=3000)
	private String posterPath;
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="user_id")
	private String userId;
	
	
	public Movie() {
		
	}
	
	


	/**
	 * Constructor.
	 * 
	 * @param movieId Movie ID from Frontend / TMDB.
	 * @param title
	 * @param overview
	 * @param posterPath
	 * @param releaseDate
	 * @param userId Logged in user
	 */
	public Movie(String movieId, String title, String overview, String posterPath, String releaseDate, String userId) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.userId = userId;
	}


	

	




	public Movie(int id, String movieId, String title, String overview, String posterPath, String releaseDate,
			String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.userId = userId;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMovieId() {
		return movieId;
	}


	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getPosterPath() {
		return posterPath;
	}


	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	
	
	
	

	

	

}
