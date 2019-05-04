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

	/**
	 * The shared attributes of 'movie' between movieService and movieFrontend has
	 * to be names alike. For e.g. poster_path. This ensures the mapping json to
	 * object automatically takes place.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "movie_id")
	private String movieId;

	@Column(name = "title")
	private String title;

	@Column(name = "overview", length = 3000)
	private String overview;

	@Column(name = "poster_path", length = 3000)
	private String poster_path;

	@Column(name = "release_date")
	private String release_date;;

	@Column(name = "user_id")
	private String userId;

	public Movie() {

	}

	/**
	 * Constructor.
	 * 
	 * @param movieId
	 *            Movie ID from Frontend / TMDB.
	 * @param title
	 * @param overview
	 * @param poster_path
	 * @param release_date
	 * @param userId
	 *            Logged in user
	 */
	public Movie(String movieId, String title, String overview, String poster_path, String release_date, String userId) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.userId = userId;
	}

	public Movie(int id, String movieId, String title, String overview, String poster_path, String release_date,
			String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
		this.poster_path = poster_path;
		this.release_date = release_date;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
	

}
